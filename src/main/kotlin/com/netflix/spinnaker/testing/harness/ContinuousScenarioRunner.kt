/*
 * Copyright 2018 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netflix.spinnaker.testing.harness

import com.netflix.spinnaker.testing.api.SpinnakerClient
import com.netflix.spinnaker.testing.scenarios.Scenario

class ContinuousScenarioRunner(
  spinnakerClient: SpinnakerClient,
  scenarios: List<Scenario>
) : AtOnceScenarioRunner(spinnakerClient, scenarios) {

  override fun plan() {
    allActivities.clear()
    allActivities.putAll(scenarios.flatMap { it.plan() }.groupBy { it.secondsOffset })
    println(allActivities)
  }
}