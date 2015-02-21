/**
 *  WatchCode
 *
 *  Copyright 2015 Paul Moulton and Emilee Chen
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "WatchCode",
    namespace: "pmoulton",
    author: "Paul Moulton and Emilee Chen",
    description: "Makes you watch less TV and code more :).",
    category: "Mode Magic",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")

preferences {
    section("Desk Light") {
        input "switch1", "capability.switch"
    }
    section("TV Power") {
    	input "switch2", "capability.switch"
    }
    section("Desk Motion") {
    	input "motion1", ""
    }
    section("Desk Siren") {
    	input "siren1", ""
    }
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	initialize()
}

def initialize() {
	switch1.on()
    def fiveMinuteDelay = 5
    runIn(fiveMinuteDelay, turnOffSwitch)
}

def updated(settings) {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
    initialize()
}

def turnOffSwitch() {
	switch1.off()
}
