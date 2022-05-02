# Inspiration
红岩网校中期考核作业
  
  
# 简要展示
## 登录注册界面
把登录注册界面独立出来。  
用了之前写的一个自定义View，来在同一个界面实现注册登录。

## 主界面
主界面是用jetpack navigation框架写的。  
单个activity与多fragment，写着是挺顺手的，不过一些navigation的坑暂时自己还不能解决 :cry:

### 灵感
由于动态更改Vector的图没有找到好的方法，所以就把色谱页中RecyclerView加载的View写死了

#### 灵感详情
界面主要是vp和自定义的Indicator  
vp中靠两个vp  
一个rv线性布局显示渐变色  
一个rv网格布局显示色卡  

### 色谱
同样的vp和自定义Indicator，不过这个界面加载的rv有动画效果  
点击进入色谱详情的界面 ~~（一个完全没有做好的共享元素动画）~~ 

#### 色谱详情
靠点击的位置找到vm中的位置找到对应实例  
点击卡片用 BottomSheetDialog 弹出详情  
点击渐变色进入渐变页面~~(同样是没有做好的共享元素动画)~~
分享按钮可以将当前渐变色分享出去

### 收藏
主要界面是rv
实现ItemTouchHelper重写clear和draw方法实现动画效果
分享按钮将当前色卡分享出去

# 心得体会
之前写项目时学习 ~~_借鉴_~~ 了别人的网络框架和对RecyclerView封装  
用协程+retrofit进行网络请求，将结果封装，不同的返回状态返回不同的结果，观察结果进行操作 
用DSL特性来写加载Adapter的内容，将ViewHolder写成ViewModel来，同时建立每一个model的缓存加载  
虽然自己肯定是写不出这么优秀的框架，不过照着学习这种框架还是有收获的  
自己对fragment的理解还是太浅了，fragment的栈管理也没有研究过  
navigation虽然写着逻辑挺清楚的，一些坑现在还是解决不了(fragment的重建问题和一些奇奇怪怪的bug)  
初始化viewmodel的时候进行网络请求，fragment只负责observe更新View是一个解决重建问题的思路(没时间尝试了)  
浅学习了下Okhttp的责任链，写了一个加载token的责任链  
收藏页面的Paging没有做  

#界面展示
//TODO 明天再贴图


