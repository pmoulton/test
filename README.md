#WatchDoge

- Watchdoge is the productivity manager for your house. 
- Set your github username and smartthings sensors to go off when you're not productive enough. 
- Great for those summer days when slacking off is all too easy.

### Version
3.0.2

### Stack
WatchDoge uses SmartThings sensors and a [Django] backend to scrape your github activity and check that you are being productive.

[Django]:https://www.djangoproject.com/

### Installation

```sh
$ git clone [git-repo-url]
$ cd watchdoge/watchcode
$ python manage.py runserver OR
$ gunicorn watchcode.wsgi:application --bind 127.0.0.1:9000 &
```

### API 
http://watchdoge.me/api/v1/check_activity/

### Todo's

 - Add coffee machine mode
 - write tests and docs