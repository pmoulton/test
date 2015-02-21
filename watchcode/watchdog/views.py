from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
import json
import util

@csrf_exempt
def check_activity(request):
    if request.method == "GET":
        return HttpResponse(status=400)
    username = request.POST['username']
    print username
    time = util.get_event_time(username, 0)
    if time == None:
        return HttpResponse(status=403)
    print time
    bool = not util.greater_than(time)
    response_data = {'error': "none", 'start': bool}
    return HttpResponse(json.dumps(response_data), content_type="application/json")