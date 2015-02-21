from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
import json

@csrf_exempt
def activity_check(request):
    if request.method == "GET":
        return HttpResponse(status=400)
    username = request.POST['username']
    response_data = {'error': "none", 'username': username}
    return HttpResponse(json.dumps(response_data), content_type="application/json")