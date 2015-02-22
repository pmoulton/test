from django.http import HttpResponse
from django.template import RequestContext, loader
from django.views.decorators.csrf import csrf_exempt
import json
import util

@csrf_exempt
def check_activity(request):
    if request.method == "GET":
        return HttpResponse(status=400)
    username = request.POST['username']
    delta = request.POST['delta']
    delta = int(delta)
    time = util.get_event_time(username, 0)
    if time == None:
        return HttpResponse(status=403)
    bool = not util.greater_than(time, delta)
    if bool:
        return HttpResponse(status=200)
    return HttpResponse(status=202)
    # response_data = {'error': "none", 'start': bool}
    # return HttpResponse(json.dumps(response_data), content_type="application/json")

def index(request):
    template = loader.get_template('index.html')
    t0 = util.delta(util.get_event_time("paulmoulton", 0), 300)
    if t0 < 0:
        t1 = 0
    else:
        t1 = t0/3

    if t1 > 0:
        labeltype = 'success'
        labeltext = "Everything is good!!"
        alerttext = "Keep Netflixin'. Watch your progress for when you should work again :)"
    else:
        labeltype = 'danger'
        labeltext = "Get Back to Work"
        alerttext = 'Push some code to Github before you watch more Parks & Rec.'
    context = RequestContext(request, {
        'percent': int(t1),
        'time': int(t0),
        'labeltext': labeltext,
        'labeltype': labeltype, 
        'alerttext': alerttext,
        'username': "paulmoulton"
    })
    return HttpResponse(template.render(context))