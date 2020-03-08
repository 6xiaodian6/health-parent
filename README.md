# health-parent项目简介

> 此项目为用户提供手机预约体检服务，并提供体检检查项和检查组管理的功能，并根据预约的套餐提供热门套餐和会员数量的图表分析的功能
# 技术栈

> dubbo、zookeeper、redis、mybatis、elementUI、Echarts、springMVC、spring Security、POI

# 模块介绍

> -- health-backend
>
> > 该模块是PC端的consumer模块，为医生提供预约管理的服务的页面资源和控制器资源
>
> -- health-common
>
> > 该模块是通用资源模块主要是实体类、静态常量、异常类和工具包资源
>
> -- health-interface
>
> > 该模块是远程服务调用接口
>
> -- health-jobs
>
> > 该模块是清理图片的工具，主要用于清理系统产生的垃圾图片
>
> -- health-mobile
>
> > 该模块是手机端的consumer模块，为用户提供手机预约的页面资源和控制器资源
>
> -- health-service-provide
>
> > 该模块是提供服务的provider模块，主要为PC端和手机端提供服务的实现


