from django.shortcuts import render
from rest_framework import status
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from snippets.models import Snippet
from snippets.serializers import SnippetSerializer
from rest_framework.response import  Response

# Create your views here.

@csrf_exempt
#해당 wrapper 사용시 request instance를 받는데 필요한 코드 쉽게 사용
@api_view(['GET', 'POST']) #api_view를 통해 함수기반 api뷰 쓸때 사용
def snippet_list(request, format=None):
    """list all code snippets, or create a new snippet."""
    if request.method =='GET':
        snippets = Snippet.objects.all()
        print(snippets) #querySet 객체
        serializer = SnippetSerializer(snippets,many=True) #모델 인스턴스를 python data형태로 직렬화
        #return JsonResponse(serializer.data,safe=False) #직렬화 한것을 Json객체로 리스판스
        return Response(serializer.data)

    elif request.method == 'POST': #역직렬화
        #data = JSONParser().parse(request)
        serializer = SnippetSerializer(data=request.data)
        if serializer.is_valid(): #유효한값이면
            serializer.save()
            #return JsonResponse(serializer.data, status=201)
            print(status.HTTP_201_CREATED)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
        #return JsonResponse(serializer.errors, status=400)

#status에 단순 숫자 상태 코드가 아닌 status 식별자로 대체해서 전달.

@csrf_exempt
@api_view(['GET', 'PUT', 'DELETE'])
def snippet_detail(request, pk, format=None): #format 접미사 추가.
    """
    Retrieve, update or delete a code snippet.
    """
    try:
        snippet = Snippet.objects.get(pk=pk) #pk값에따라 가져온다
    except Snippet.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = SnippetSerializer(snippet) #직렬화
        return Response(serializer.data) #JSON포맷으로 변경 후 response

    elif request.method == 'PUT':
        #data = JSONParser().parse(request)
        serializer = SnippetSerializer(snippet, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        snippet.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)