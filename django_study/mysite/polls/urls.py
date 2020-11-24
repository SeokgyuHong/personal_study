from django.urls import path
from . import views
#url을 매핑 시켜주는것 특정 뷰에 매핑 시켜주는 과정 
#polls에 있는 url을 메인 프로젝트 url.py에서 참조할수있게 해줘야함 
urlpatterns = [
    path('', views.index, name='index'),
]