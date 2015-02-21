from django.conf.urls import patterns, include, url
import views

urlpatterns = patterns('',
    url(r'^api/v1/check_activity/', views.activity_check,name="activity_check"),
    )