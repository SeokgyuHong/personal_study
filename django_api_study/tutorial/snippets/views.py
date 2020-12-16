from django.shortcuts import render
from rest_framework import status
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.decorators import api_view
# api_view는 function based view에 대응 APIView는 class based view에 대응
from rest_framework.parsers import JSONParser
from snippets.models import Snippet
from snippets.serializers import SnippetSerializer
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import mixins
from rest_framework import generics
from django.http import Http404
from django.contrib.auth.models import User
from snippets.serializers import UserSerializer
from rest_framework import permissions
from snippets.permissions import IsOwnerOrReadOnly
from rest_framework.reverse import reverse
from rest_framework import renderers
from rest_framework import viewsets #views의 집합으로 인스턴스화되는 마지막 순간에 method handler집합에 의해 바인딩된다.
from rest_framework.decorators import action


# Create your views here
# mixin class를 사용하는건 view 재사용을 해서 코드를 줄이는것
# res framework는 이미 mixed 된 view를 제공해주기때문에 한번더 코드를 줄일수있다.

class SnippetViewSet(viewsets.ModelViewSet):
    """해당 뷰셋이 자동으로 list create retrieve update destroy등 제공 추가적으로 highjlight까지 제공 (primary key 대신에 highlight로 엔티티간 매핑)"""
    queryset = Snippet.objects.all()
    serializer_class = SnippetSerializer
    permission_classes = [permissions.IsAuthenticatedOrReadOnly,IsOwnerOrReadOnly]
    
    """커스텀 액션을 추가하기위해서 action 데코레이터 사용 디폴트로 get request에 응답하지만 post request에 응답하게도 가능"""
    @action(detail=True, renderer_classes=[renderers.StaticHTMLRenderer])
    def highlight(self, request, *args, **kwargs):
        snippet = self.get_object()
        return Response(snippet.highlighted)

    def perform_create(self, serializer):
        serializer.save(owner=self.request.user)

#아래 3가지를 ViewSwet으로 리팩토링
# class SnippetList(generics.ListCreateAPIView):  # 목록/생성
#     queryset = Snippet.objects.all()
#     serializer_class = SnippetSerializer
#     permission_classes = [permissions.IsAuthenticatedOrReadOnly]
#     # 직렬화할 클래스 지정만해주면 됨
#     def perform_create(self, serializer):
#         serializer.save(owner=self.request.user)
#
# #조회/ 수정/삭제는 권한이 잇을경우에만 가능
# class SnippetDetail(generics.RetrieveUpdateDestroyAPIView):  # 조회/수정/삭제
#     permission_classes = [permissions.IsAuthenticatedOrReadOnly,
#                           IsOwnerOrReadOnly]
#     queryset = Snippet.objects.all()
#     serializer_class = SnippetSerializer
#
# class SnippetHighlight(generics.GenericAPIView):
#     queryset = Snippet.objects.all()
#     renderer_classes = [renderers.StaticHTMLRenderer]
#
#     def get(self, request, *args, **kwargs):
#         snippet = self.get_object()
#         return Response(snippet.highlighted)

class UserViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer

#아래 두가지를 viewset으로 리팩토링
# class UserList(generics.ListAPIView):
#     queryset = User.objects.all()
#     serializer_class = UserSerializer
#
#
# class UserDetail(generics.RetrieveAPIView):
#     queryset = User.objects.all()
#     serializer_class = UserSerializer
#

@api_view(['GET'])
def api_root(request, format=None):
    return Response({
        'users': reverse('user-list', request=request, format=format),
        'snippets': reverse('snippet-list', request=request, format=format)
    })


# #제네릭 뷰 상속을 통해 구현, 장고에서 자주 사용되는 개발패턴을 모아놓은 뷰

# class SnippetDetail(mixins.RetrieveModelMixin,
#                     mixins.UpdateModelMixin,
#                     mixins.DestroyModelMixin,
#                     generics.GenericAPIView):
#     queryset = Snippet.objects.all()
#     serializer_class = SnippetSerializer
#
#     def get(self, request, *args, **kwargs):
#         return self.retrieve(request, *args, **kwargs)
#
#     def put(self, request, *args, **kwargs):
#         return self.update(request, *args, **kwargs)
#
#     def delete(self, request, *args, **kwargs):
#         return self.destroy(request, *args, **kwargs)


# class SnippetList(mixins.ListModelMixin,mixins.CreateModelMixin,generics.GenericAPIView):
#     queryset = Snippet.objects.all()
#     serializer_class = SnippetSerializer
#     def get(self,request,*args,**kwargs):
#         return self.list(request,*args,**kwargs)
#     def post(self,request,*args,**kwargs):
#         return self.create(request,*args,**kwargs)

# 클래스기반 뷰 생성 -----
# class SnippetList(APIView):
#     def get(self, request, format=None):
#         snippets = Snippet.objects.all()
#         serializer = SnippetSerializer(snippets, many=True)
#         return Response(serializer.data)
#     def post(self, request, format=None):
#         serializer = SnippetSerializer(data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data,status=status.HTTP_201_CREATED)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
# class SnippetDetail(APIView):
#     def get_object(self, pk):
#         try:
#             return Snippet.objects.get(pk=pk)
#         except Snippet.DoesNotExist:
#             raise Http404 #raise 에러 발생
#     def get(self, request, pk, format=None):
#         snippet = self.get_object(pk)
#         serializer = SnippetSerializer(snippet)
#         return Response(serializer.data)
#     def put(self,request,pk,format=None):
#         snippet = self.get_object(pk)
#         serializer = SnippetSerializer(snippet, data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data)
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#     def delete(self,request,pk,format=None):
#         snippet = self.get_object(pk)
#         snippet.delete()
#         return Response(status=status.HTTP_204_NO_CONTENT)

# @csrf_exempt
# #해당 wrapper 사용시 request instance를 받는데 필요한 코드 쉽게 사용
# @api_view(['GET', 'POST']) #api_view를 통해 함수기반 api뷰 쓸때 사용
# def snippet_list(request, format=None):
#     """list all code snippets, or create a new snippet."""
#     if request.method =='GET':
#         snippets = Snippet.objects.all()
#         print(snippets) #querySet 객체
#         serializer = SnippetSerializer(snippets,many=True) #모델 인스턴스를 python data형태로 직렬화
#         #return JsonResponse(serializer.data,safe=False) #직렬화 한것을 Json객체로 리스판스
#         return Response(serializer.data)
#
#     elif request.method == 'POST': #역직렬화
#         #data = JSONParser().parse(request)
#         serializer = SnippetSerializer(data=request.data)
#         if serializer.is_valid(): #유효한값이면
#             serializer.save()
#             #return JsonResponse(serializer.data, status=201)
#             print(status.HTTP_201_CREATED)
#             return Response(serializer.data, status=status.HTTP_201_CREATED)
#         return Response(serializer.errors,status=status.HTTP_400_BAD_REQUEST)
#         #return JsonResponse(serializer.errors, status=400)
#
# #status에 단순 숫자 상태 코드가 아닌 status 식별자로 대체해서 전달.
#
# @csrf_exempt
# @api_view(['GET', 'PUT', 'DELETE'])
# def snippet_detail(request, pk, format=None): #format 접미사 추가.
#     """
#     Retrieve, update or delete a code snippet.
#     """
#     try:
#         snippet = Snippet.objects.get(pk=pk) #pk값에따라 가져온다
#     except Snippet.DoesNotExist:
#         return Response(status=status.HTTP_404_NOT_FOUND)
#
#     if request.method == 'GET':
#         serializer = SnippetSerializer(snippet) #직렬화
#         return Response(serializer.data) #JSON포맷으로 변경 후 response
#
#     elif request.method == 'PUT':
#         #data = JSONParser().parse(request)
#         serializer = SnippetSerializer(snippet, data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data)
#
#         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
#
#     elif request.method == 'DELETE':
#         snippet.delete()
#         return Response(status=status.HTTP_204_NO_CONTENT)
