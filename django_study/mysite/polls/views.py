
from django.shortcuts import get_object_or_404,render
from django.http import HttpResponse
from django.http import Http404
from django.template import loader
from .models import Question
# def index(request):
    
#     latest_question_list = Question.objects.order_by('-pub_date')[:5]
#     #polls/index.html 템플릿 불러온후 context 객체 전달 
#     #context는 템플릿에서 쓰이는 변수명과 파이썬 객체를 연결하는 사전형 값 
#     template = loader.get_template('polls/index.html')
#     context = {'latest_question_list' : latest_question_list}
#     return HttpResponse(template.render(context,request))
# # Create your views here.

def index(request):
    latest_question_list = Question.objects.order_by('-pub_date')[:5]
    context = {'latest_question_list':latest_question_list}
    return render(request,'polls/index.html',context)
    #render함수는 request객체를 첫 인자 , 템플릿 이름을 두번째 인자,context객체를 세번째 인자로 받음.
    #표현된 템플릿의 httpResponse객체가 반환된다.

#생성한 뷰 함수 
def detail(request,question_id):
    question = get_object_or_404(Question,pk=question_id)
    return render(request,'polls/detail.html',{'question':question})

def results(request,question_id):
    response = "You're looking at the results of question %s."
    return HttpResponse(response % question_id)

def vote(request, question_id):
    return HttpResponse("You're voting on question %s."% question_id)


