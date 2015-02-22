import requests
import datetime

def get_event_time(username="pmoulton", depth=0):
    """Get latest Github activity for a given username and depth"""
    url = "https://api.github.com/users/" + username + "/events/public"
    r = requests.get(url)
    if r.status_code >= 400:
        return None
    activity = r.json()
    t = activity[depth]['created_at']
    return datetime.datetime.strptime(t,"%Y-%m-%dT%H:%M:%SZ")

def greater_than(test, delta=30):
    """Return True if the last commit time 'test' is in the
    last 30 seconds"""
    delta = datetime.timedelta(seconds=delta)
    current = datetime.datetime.utcnow()
    return  test + delta >= current
