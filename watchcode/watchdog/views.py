from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
import json
import util

@csrf_exempt
def check_activity(request):
    if request.method == "GET":
        return HttpResponse(status=400)
    username = request.POST['username']
    time = util.get_event_time(username, 0)
    if time == None:
        return HttpResponse(status=403)
    bool = not util.greater_than(time)
    if bool:
    	return HttpResponse(status=200)
    return HttpResponse(status=202)
    # response_data = {'error': "none", 'start': bool}
    # return HttpResponse(json.dumps(response_data), content_type="application/json")