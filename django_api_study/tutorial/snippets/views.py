from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from snippets.models import Snippet
from snippets.serializers import SnippetSerializer

# Create your views here.

@csrf_exempt
def snippet_list(request):
    if request.method =='GET':
        snippets = Snippet.objects.all()
        serializer = SnippetSerializer(snippets,many=True) #모델 인스턴스를 python data형태로 직렬화
        return JsonResponse(serializer.data,safe=False) #직렬화 한것을 Json객체로 리스판스
    
    elif request.method == 'POST': #역직렬화
        data = JSONParser().parse(request) 
        serializer = SnippetSerializer(data=data)
        if serializer.is_valid(): #유효한값이면
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)


@csrf_exempt
def snippet_detail(request, pk):
    """
    Retrieve, update or delete a code snippet.
    """
    try:
        snippet = Snippet.objects.get(pk=pk) #pk값에따라 가져온다
    except Snippet.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'GET':
        serializer = SnippetSerializer(snippet) #직렬화
        return JsonResponse(serializer.data) #JSON포맷으로 변경 후 response

    elif request.method == 'PUT':
        data = JSONParser().parse(request)
        serializer = SnippetSerializer(snippet, data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data)
        return JsonResponse(serializer.errors, status=400)

    elif request.method == 'DELETE':
        snippet.delete()
        return HttpResponse(status=204)