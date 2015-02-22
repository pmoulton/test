
var num = document.getElementById('theInteger');
var counter = num.getAttribute('integer');
var label = document.getElementById('bartext')
var g = document.getElementById('g_alert');
var b = document.getElementById('b_alert');


$(document).ready(function () {
    
    ViewModel = function(){
        var self = this;
        this.num = ko.observable(counter);
        this.okay = ko.pureComputed(function() {
          return this.num() > 0;
        }, this);
        console.log(this.num())
        console.log(this.okay())
    };

    ko.applyBindings(new ViewModel);
});



function countdown() {
  setTimeout('decrement()', 1000);
}

function decrement() {
  var bar = $(".progress-bar")
  if (counter > 0) {
    counter--;
    label.textContent = counter;
    console.log(counter)
    bar.css('width', counter/3 + '%');
    setTimeout('decrement()',1000);
  }
  else {
    $("#g_alert").hide();
    $("#b_alert").show();
    $("#g_box").hide();
    $("#b_box").show();
  }
}
$("#b_alert").hide();
$("#b_box").hide();
countdown();