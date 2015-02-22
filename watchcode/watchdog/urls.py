from django.conf.urls import patterns, url
from django.views.generic import TemplateView
import views

urlpatterns = patterns('',
    url(r'^api/v1/check_activity/', views.check_activity,name="check_activity"),
    url(r'^index.html', views.index, name="home")
    )