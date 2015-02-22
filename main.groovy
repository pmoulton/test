/**
 *  CodeDawg
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
    name: "CodeDawg",
    namespace: "pmoulton",
    author: "Paul Moulton and Emilee Chen",
    description: "Does the thing now.",
    category: "My Apps",
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
    }
    section("Desk Alarm") {
        input "alarm1", "capability.alarm"
    }
}


def installed() {
    log.debug "Installed with settings: ${settings}"
    initialize()
}

def initialize() {
    subscribe(motion_detector, "motion", motionHandler)
    // Starting config
    switch1.off()
    switch2.on()
    alarm1.off()
    // Run main routine
    main()
}

def motionHandler(evt)
{
    log.debug "${evt.name} is ${evt.value}."
    if (evt.value == "active") {
        alarm1.off()
    }
}

def updated(settings) {
    log.debug "Updated with settings: ${settings}"
    unsubscribe()
    initialize()
}


def main() {
    def b = wake_user()
    if (b) {
        switch1.on()
        alarm1.strobe()
        def thirtySeconds = 30
        runIn(thirtySeconds, main)
    }
    else {
        switch1.off()
        alarm1.off()
        def fiveSeconds = 5
        runIn(fiveSeconds, main)
    }
}

def wake_user() {
    
    // Define the keys of the postBody
    def postBody = [
        username: "paulmoulton",
        delta: "300"
    ]

    // Prepare the parameters
    def params = [
        uri: "http://watchdoge.me/api/v1/check_activity/",
        body: postBody
    ]

    log.debug postBody


    httpPost(params){
        response ->
        if(response.status != 200)
        {
            log.error "Received HTTP error ${response.status}. Check your keys!"
            return false
        }
        else
        {
            log.debug "HTTP response received [$response.status]"
            return true
        }
    }
}