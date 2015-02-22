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
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	initialize()
}

def initialize() {
	// Starting config
   	switch1.off()
    switch2.on()
    // Run main routine
    main()


}

def updated(settings) {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
    initialize()
}


def main() {
    def fiveSeconds = 5
	runIn(fiveSeconds, routine)
}

def routine() {
	def b = wake_user()
    if (b) {
    	wake_routine()
    }
   	else {
    	main()
    }
}

def wake_routine() {
	switch1.on()
    switch1.off()
   	switch1.on()
    switch1.off()
   	switch1.on()
    switch1.off()
   	switch1.on()
    switch1.off()
}

def wake_user() {
    
    // Define the keys of the postBody
    def postBody = [
        username: "pmoulton"
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