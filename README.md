# 实验三 Android 界面组件

移动软件开发课程实验，作者：王鑫懿（学号 121052024077）。

五个互相独立的界面，分别练习列表、对话框、菜单、上下文操作模式和 RecyclerView，主界面五个按钮进入对应界面。

## 主要内容

- ListView + SimpleAdapter：显示六种动物的图文列表，点击弹 Toast 并发一条通知
- 自定义 AlertDialog：setView 加载自定义布局，做出“ANDROID APP”登录框
- XML 菜单：字体大小（10/16/20sp 子菜单）、普通菜单项、字体颜色（红/黑）
- ActionMode：长按进入多选，标题显示“N selected”，可批量删除
- RecyclerView：ViewHolder 复用、点击提示、新增记录后局部刷新（notifyItemInserted）

## 如何运行

1. 用 Android Studio 打开本目录（不要打开上层文件夹）
2. 首次 Sync 需要联网下载 Gradle 8.7 与依赖；国内网络若拉不动 Google Maven，可保留 `settings.gradle` 里的阿里云镜像
3. 选择模拟器或真机，运行 `app`

环境配置：Android Gradle Plugin 8.5.2 / Gradle 8.7 / compileSdk 34 / minSdk 24 / JDK 17

## 目录

```
app/src/main/java/        源码
app/src/main/res/         布局、字符串、图片等资源
docs/                     实验报告（Word）
```

## 说明

通知在 Android 13 及以上需要 POST_NOTIFICATIONS 权限，代码里做了运行时申请；Android 8.0 起需要 NotificationChannel，也已适配。
