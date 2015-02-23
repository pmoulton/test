#CodeWatchDoge
###CodeWatchDoge turns on your desk lamp and turns off your TV when you haven't pushed enough code to Github recently
- CodeWatchDoge is the premiere productivity manager for your house. 
- Set your github username and smartthings sensors to go get your back to work
- Great for those summer days when slacking off is all too easy.
- Version 0.0.1 turns off your TV when you haven't committed in 5 minutes, turns on your desk light, and strobes the alarm if you aren't at your desk in 30 seconds (detected by a motion sensor).

### Version
0.0.1

### Stack
CodeWatchDoge uses SmartThings sensors and a [Django] backend to make [Github] API requests to monitor your commit history and ensure you're productive.

[Django]:https://www.djangoproject.com/
[Github]:https://www.github.com/

### Installation

```sh
$ pip install Django=1.7.4
$ git clone https://github.com/pmoulton/watchdoge
$ cd watchdoge/watchcode
$ python manage.py runserver OR
$ gunicorn watchcode.wsgi:application --bind 127.0.0.1:9000 &
```

### Todo's

 - Add coffee machine mode
 - write tests and docs
