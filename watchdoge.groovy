/**
 *  WatchDoge
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
    name: "WatchDoge",
    namespace: "pmoulton",
    author: "Paul Moulton and Emilee Chen",
    description: "Does cool things",
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
        input "motion1", "capability.motionSensor"
    }f
    section("Desk Siren") {
        input "siren1", "capability.alarm"
    }
}

def installed() {
    log.debug "Installed with settings: ${settings}"
    initialize()
}

def initialize() {
    
    // Prepare the package to be sent
    def params = [
        uri: "http://watchdoge.me/api/v1/check_activity/",
        username: "pmoulton"
    ]

    log.debug postBody

    switch1.on()
    httpPost(params){
            if (response.status == 200) {
                log.error "Received ok reponse. Do not start disruptive process."
                switch1.off()
                switch1.on()
            }
            else {
                log.debug "Wake the user."
                switch1.off()
            }
    }
    //switch1.on()
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
