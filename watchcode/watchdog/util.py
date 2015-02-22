import requests
import datetime

def get_event_time(username, depth):
    """Get latest Github activity for a given username and depth"""
    url = "https://api.github.com/users/" + username + "/events/public?access_token=e33ee87d7a806d3d8ea8d6547b539d1465f1038e"
    r = requests.get(url)
    if r.status_code >= 400:
        return None
    activity = r.json()
    t = activity[depth]['created_at']
    return datetime.datetime.strptime(t,"%Y-%m-%dT%H:%M:%SZ")

def greater_than(test, delta=300):
    """Return True if the last commit time 'test' is in the
    last 30 seconds"""
    delta = datetime.timedelta(seconds=delta)
    current = datetime.datetime.utcnow()
    return  test + delta >= current

def delta(test, delta=300):
    current = datetime.datetime.utcnow()
    t = test + datetime.timedelta(seconds=delta)
    t = t - current
    return t.total_seconds()

# if __name__=="__main__":
#     print delta(get_event_time("paulmoulton", 0))