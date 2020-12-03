from django.urls import path
from . import views
#url을 매핑 시켜주는것 특정 뷰에 매핑 시켜주는 과정 
#polls에 있는 url을 메인 프로젝트 url.py에서 참조할수있게 해줘야함 
app_name ='polls'
urlpatterns = [
    # ex: /polls/
    path('',  views.IndexView.as_view(), name='index'),
    # ex: /polls/5/
    path('<int:pk>/',views.DetailView.as_view(), name='detail'),
    # ex: /polls/5/results/
    path('<int:pk>/results/', views.ResultsView.as_view(), name='results'),
    # ex: /polls/5/vote/
    path('<int:question_id>/vote/', views.vote, name='vote'),
]
