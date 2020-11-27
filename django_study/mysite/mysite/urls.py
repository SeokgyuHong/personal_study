"""mysite URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path,include
#polls/로 들어오는 url은 polls.urls로 처리하기로 함 
#ex ) polls/34/로 들어올경우 polls/까지 여기서 처리하고 버린다음 34/뒤로 넘김
urlpatterns = [
    path('admin/', admin.site.urls),
    path('polls/',include('polls.urls'))
]
