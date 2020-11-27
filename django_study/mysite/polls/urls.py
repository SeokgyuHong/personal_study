from django.urls import path
from . import views
#url을 매핑 시켜주는것 특정 뷰에 매핑 시켜주는 과정 
#polls에 있는 url을 메인 프로젝트 url.py에서 참조할수있게 해줘야함 
app_name ='polls'
urlpatterns = [
    path('', views.index, name='index'),
    #int:question_id = :question_id로 보내야한다. 해당값을
    path('<int:question_id>/',views.detail,name='detail'),
    path('<int:question_id>/results/', views.results,name='results'),
    path('<int:question_id>/vote/',views.vote,name='vote'),
]