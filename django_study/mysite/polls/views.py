
from django.shortcuts import get_object_or_404, render
from django.urls import reverse
from django.http import HttpResponse, HttpResponseRedirect
from django.http import Http404
from django.template import loader
from django.views import generic
from .models import Question
from django.utils import timezone
# def index(request):
    
#     latest_question_list = Question.objects.order_by('-pub_date')[:5]
#     #polls/index.html 템플릿 불러온후 context 객체 전달 
#     #context는 템플릿에서 쓰이는 변수명과 파이썬 객체를 연결하는 사전형 값 
#     template = loader.get_template('polls/index.html')
#     context = {'latest_question_list' : latest_question_list}
#     return HttpResponse(template.render(context,request))
# # Create your views here.
class IndexView(generic.ListView): #조건에맞는 여러객체 보여줌
    template_name = 'polls/index.html' #오버라이딩
    context_object_name = 'latest_question_list' #오버라이딩
    def get_queryset(self):
        """ Return the last five published question,"""
        return Question.objects.filter(
        pub_date__lte=timezone.now()
        ).order_by('-pub_date')[:5]
#기존 함수형 뷰 
def index(request):
    latest_question_list = Question.objects.order_by('-pub_date')[:5]
    context = {'latest_question_list': latest_question_list}
    return render(request, 'polls/index.html', context)
    #render함수는 request객체를 첫 인자 , 템플릿 이름을 두번째 인자,context객체를 세번째 인자로 받음.
    #표현된 템플릿의 httpResponse객체가 반환된다.
class DetailView(generic.DetailView): #객체 한개에 대한 상세정보를 보여준다.
    model = Question
    template_name = 'polls/detail.html'
    def get_queryset(self):
        return Question.objects.filter(pub_date__lte=timezone.now())


class ResultsView(generic.DetailView):
    model = Question
    template_name = 'polls/results.html'



#생성한 뷰 함수 
def detail(request,question_id):
    question = get_object_or_404(Question,pk=question_id)
    return render(request,'polls/detail.html',{'question':question})

def results(request, question_id):
    question = get_object_or_404(Question, pk=question_id)
    return render(request, 'polls/results.html', {'question': question})

def vote(request, question_id):
    question = get_object_or_404(Question, pk=question_id)
    try:
        selected_choice = question.choice_set.get(pk=request.POST['choice'])
    except (KeyError, Choice.DoesNotExist):
        # Redisplay the question voting form.
        return render(request, 'polls/detail.html', {
            'question': question,
            'error_message': "You didn't select a choice.",
        })
    else:
        selected_choice.votes += 1
        selected_choice.save()
        # Always return an HttpResponseRedirect after successfully dealing
        # with POST data. This prevents data from being posted twice if a
        # user hits the Back button.
        return HttpResponseRedirect(reverse('polls:results', args=(question.id,)))


