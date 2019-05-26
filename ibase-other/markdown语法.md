```flow
st=>start: 开始
op=>operation: My Operation
cond=>condition: Yes or No?
e=>end
st->op->cond
cond(yes)->e
cond(no)->op
&``

	
	


  
```



#1. 标题
# 第一级标题 `<h1>` 
## 第二级标题 `<h2>` 
### 第三级标题 `<h3>` 
#### 第二四级标题 `<h4>` 
##### 第五级标题 `<h5>` 
###### 第六级标题 `<h6>`
#2.段落

段落  
段落

#3.区块引用
> 区块引用
> > 嵌套引用
> > >三嵌套引用
> > > > 四嵌套引用
#4.代码区块
	fun main(args: Array<String>) {
	   println("Hello World!")

	   println("sum = ${sum(34, 67)}")
	   println("sum = ${sum(34, 67)}")
	   println("sum = ${sum(34, 6, 57, 34)}")
	
	   printSum(237, 57)
	   printSum(234, 567, 8)
	   vars(1, 4, 6, 78, 0, 6, 9, 8)


​	
	   val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
	   println("sumLambda = ${sumLambda(3, 6)}")


​	
	//    if (args.size < 2) {
	//        println("Two integers expected")
	//        return
	//    }
	   testFor()


​	
	   val a: Int = 1000
	   println(a === a)//true 值相等，对象地址相等
	
	   //经过了装箱，创建了两个不同的对象
	   val boxedA: Int? = a
	   val anotherBoxedA: Int? = a
	
	   //虽然经过了装箱，但是值是相等的，都是10000
	   println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样
	   println(boxedA == anotherBoxedA) // true，值相等
	}

#5.颜色
<font color ="red">示例md代码:</font>
#6.无序列表
+ 1
+ 2
+ 3
- a
- b
- c
* 1
* 2
* 3 
#7.有序列表
1. 1
2. 2
3. 3    
#8.分割线
***
---
_____

#9.链接

[GitHub](http://github.com)
自动生成连接  <http://www.github.com/>

[GitHub][1]
[1]:http://github.com
自动生成连接  <http://www.github.com/>

#10.图片

![GitHub set up](http://zh.mweb.im/asset/img/set-up-git.gif)

###`格式: ![Alt Text](url)`

#11.标签
``Ctrl+A`` 、``Ctrl+C``、``Ctrl+V``

#12.表格
第一格表头 | 第二格表头
--- | ---
内容单元格 第一列第一格 | 内容单元格第二列第一格
内容单元格 第一列第二格 多加文字 | 内容单元格第二列第二格
内容单元格 第一列第三格 多加文字 | 内容单元格第二列第三格
内容单元格 第一列第四格 多加文字 | 内容单元格第二列第四格

#13.流程图

```flow
st=>start: Start|past:>http://www.google.com[blank]
e=>end: End:>http://www.google.com
op1=>operation: My Operation|past
op2=>operation: Stuff|current
sub1=>subroutine: My Subroutine|invalid
cond=>condition: Yes
or No?|approved:>http://www.google.com
c2=>condition: Good idea|rejected
io=>inputoutput: catch something…|request

st->op1(right)->cond
cond(yes, right)->c2
cond(no)->sub1(left)->op1
c2(yes)->io->e
c2(no)->op2->e
```