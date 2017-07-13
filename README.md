# HealthMonitorWithBluetooth

## Abstract:

​	本项目用于实现通过蓝牙设备传输采集的脉搏与心电数据，并进行相应的显示与病理分析。

## 简要操作手册

​	本项目主要分为如下几个功能模块:登录模块、注册模块、数据采集模块、历史记录查询模块、个人信息设置模块。

1. **登录模块: **用于完成用户登录，当用户名与密码匹配成功时，跳转到操作主界面。并能对异常情况，如*密码错误*、*用户名为空*、*未输入密码*等进行相应的提示。
2. **注册模块: **用于新用户的注册，并进行相应的数据校验，例如用户名与密码长度与格式，年龄与联系电话的范围。
3. **数据采集模块: **通过扫描、发现、链接蓝牙设备完成采集准备，并将发送来的数据进行格式处理与分类显示。实现采集数据的直观、动态显示。
4. **历史记录查询模块: **通过选定时间范围，搜索选定时间段范围内所有的采集数据信息，再通过用户进一步选择来显示相应的历史采集数据信息。
5. **个人信息设置: **用于实现对当前用户信息的修改与保存。

## 工程文件简介

本工程 采用`MVVM`+`DataBinding`的模式进行设计。

* **`Model `** — 用于定义工程中的数据结构、适配器、自定义控件等

  * **`Adapters `** — 工程中所有的适配器

    * **`BindingAdapter`** — 所有控件的`DataBinding`的自定义`BindingAdapter`

      * **`BluetoothTextBindingAdapter.java`** — 用于蓝牙设备相关操作。

        | 方法名                     | 备注              |
        | ----------------------- | --------------- |
        | clearReceiveText        | 用于实现数据接收时显示的清除  |
        | updateReceiveText       | 用于实现数据接收时切换接收按钮 |
        | setLinearLayoutHandover | 用于实现开关蓝牙时界面的变换  |

      * **`CalendarPickerBindingAdapter.java`** — 用于自定义控件`CalenderPicker`相关的操作的适配器。

        | 方法名                   | 备注                        |
        | --------------------- | ------------------------- |
        | setCalendarPickerInit | 用于初始化CalendarPickerView控件 |

      * **`ChooseDateRangeBindingAdapter`** — 用于自定义控件`CalenderPicker`选取时间范围后的相关操作的适配器。

        | 方法名               | 备注                        |
        | ----------------- | ------------------------- |
        | setDoneButtonText | 选定时间范围后确认选择后的`Button`控件显示 |

      * **`LineChartBindingAdapter`** — 用于自定义控件`LineChart`相关操作的适配器。

        | 方法名          | 备注                         |
        | ------------ | -------------------------- |
        | setLineChart | 用于操作所有关于`LineChart`控件的逻辑操作 |

      * **`ListViewBindingAdapter.java`** — 用于控件`ListView`相关配置的适配器。

        | 方法名                   | 备注                      |
        | --------------------- | ----------------------- |
        | setListViewBorderLine | 用于控制`ListView`是否显示头尾分割线 |

      * **`RegisterBindingAdapter`** — 用于控制注册模块各个`TextInputLayout`控件的操作。

        | 方法名            | 备注             |
        | -------------- | -------------- |
        | checkName      | 校验与保存输入的*用户名*  |
        | checkPwd       | 校验与保存输入的*密码*   |
        | checkRePwd     | 校验两次*密码*输入是否相同 |
        | checkAge       | 校验与保存输入的*年龄*   |
        | checkTelephone | 校验与保存输入的*电话*   |

      * **`ViewPagerBindingAdapter.java`** — 用于自定义控件`CustomViewPage`的相应操作的响应。

        | 方法名          | 备注                          |
        | ------------ | --------------------------- |
        | setViewPager | 相应`CustomViewPage`控件切换窗口的相应 |

    * **`MethodsAdapters`** — 自定义方法的适配器

      * **`CommentPagerAdapter.java`** — 自定义Pager的适配器。
      * **`ListAdapter.java `** — 可以用于`Databinding`的`ListView`的适配器。

  * **`Bean`** — 工程中所有的数据结构

    * **`BaseActionbarconf.java`** — 用于控制自定义`ActionBar`。
    * **`BtDeviceItem.java`** — 蓝牙设备数据结构类。
    * **`ReceptionData.java`** — 接收数据格式类。
    * **`SystemInfo.java`** — 用于描述系统中各种信息。
    * **`User.java`** — 用于描述登录用户信息。

  * **`Enums`** — 工程中所有定义的枚举变量

    * **`CmdCode.java`** — 操作码枚举。
    * **`ErrorCode.java`** — 错误码枚举。

  * **`Widget`** — 自定义控件

    * **`CustomViewPage.java`** — 自定义`ViewPager`，可以自定义是否可以禁止部分滑动操作。
    * **`OptimizationToast.java`** — 自定义`Toast`，使得多次单击不会连续弹出显示窗口。

* **`View`** — 所有直接界面操作类

  * **`Avtivity`** — 所有界面的`*Activity.java`类文件。
    * **`DataAcceptanceActivity.java`** — 用于定义数据接收功能界面的`Activity`。
    * **`HistoryActivity.java`** — 用于定义历史数据记录搜索功能界面的`Activity`。
    * **`HistoryDetailActivity.java`** — 用于详细显示单条历史记录数据功能界面的`Activity`。
    * **`HomeActivity.java`** — 用于定义登录后系统功能分类展示与选择界面的`Activity`。
    * **`LoginActivity.java`** — 用于定义用户登录界面的`Activity`。
    * **`MoniterActivity.java`** — 用于定义蓝牙设备扫描与配对功能界面的`Activity`。
    * **`RegisterActivity.java`** — 用于定义用户注册界面的`Activity`。
    * **`SetActivity.java`** — 用于修改用户个人信息界面的`Activity`。
  * **`Fragment`** — 所有的`*Fragment.java`类文件
    * **`MoniterFragment.java`** — 单个波形显示界面的`Fragment`。

* **`ViewModel`** — 所有视图模型操作类

  * **`BluetoothViewModel`** — 所有蓝牙相关的`ViewModel`

    * **`BtAdapterViewModel.java`** — 本机蓝牙各种状态的处理。
    * **`BtDeviceViewModel.java`** — 扫描蓝牙设备时各种状态的处理。
    * **`BtReceiver.java`** — 自定义蓝牙接收广播。

  * **`ButtonViewModel`** — 所有按键相关的`ViewModel`

    * **`ChooseDateRangeButtonViewModel.java`** — 用于响应历史记录查询时间段范围选择相应。
    * **`ClearButtonViewModel.java`** — 用于清除实时蓝牙传输数据的显示。
    * **`HomeButtonViewModel.java`** — 用于响应`HomeActivity`中不同按键的选择。
    * **`LoginButtonViewModel.java`** — 用于响应用户点击*登录*按钮的选择。
    * **`OnGetClickListener.java`** — 用于实现按键点击相应结果响应的回调**接口**。
    * **`OnGetNewActivity.java`** — 用于实现按键点击选择新`Activity`结果响应的回调**接口**。
    * **`OnReceiveButtonListener.java`** — 接收按钮的回调**接口**，用于在开启与关闭Service时，在Activity中实现与界面相关的操作。
    * **`ReceiveButtonViewModel.java`** — 用于响应点击接收按键的操作。
    * **`RegisterButtonViewModel.java`** — 用于响应点击注册按钮的操作。
    * **`ReviseUserButtonViewModel.java`** — 用于响应点击确定修改按钮的操作。
    * **`SearchButtonViewModel.java`** — 用于响应点击设备搜索按钮的操作。
    * **`SearchHistoryButtonViewModel.java`** — 用于响应点击搜索历史记录按钮的操作。
    * **`SwitchButtonViewModel.java`** — 用于响应点击开闭本机蓝牙按钮的操作。
    * **`SwitchPagerButtonViewModel.java`** — 用于响应点击切换`ViewPager`显示界面按钮的操作。

  * **`DatabaseViewMode`** — 数据库有关操作类

    **Note:** *为了方便数据库调试，工程引用工具`debug-db`进行在线调试，具体见[Android-Debug-Database](https://github.com/amitshekhariitbhu/Android-Debug-Database)*

    * **` CustomSQLiteOpenHelper.java`** — 用于创建数据库。
    * **`DataBaseStringHelper.java`** — 用于定义数据库相关字符串。


    * **`DBOperationOfBase.java`** — 数据库基础操作类。

      | 方法名                | 备注         |
      | ------------------ | ---------- |
      | checkInfoIntegrity | 校验用户信息的完整性 |

    * **`DBOperationOfLogin.java`** — 登录相关数据库操作

      | 方法名        | 备注        |
      | ---------- | --------- |
      | checkLogin | 校验登录信息的正误 |

    * **`DBOperationOfRegister.java`** — 注册相关数据库操作。

      | 方法名            | 备注       |
      | -------------- | -------- |
      | registerUser   | 进行新用户注册  |
      | reviseUserInfo | 进行用户信息修改 |

    * **`DBOperationOfSaveReceive.java`** — 保存采集数据相关数据库操作。

      | 方法名             | 备注     |
      | --------------- | ------ |
      | saveReceiveData | 保存采集数据 |

    * **`DBOperationOfSearchHistory.java`** — 历史记录查询相关数据库操作。

      | 方法名                                  | 备注                  |
      | ------------------------------------ | ------------------- |
      | searchHistoryRecordNameWithDateRange | 根据时间段筛选历史记录，并返回记录名称 |
      | searchHistoryMonitorWithRecordName   | 根据历史记录名称查询历史接收数据信息  |

    * **`SQLiteOperation.java`** — 完成数据库所有的增、删、改、查、相关操作。

      | 方法名                                    | 备注                                       |
      | -------------------------------------- | ---------------------------------------- |
      | insertItem2User                        | 用于向数据表`user`中插入数据                        |
      | updateItemOfUserByUserName             | 更新表`user`中指定用户名的数据                       |
      | updateItemOfUserById                   | 更新表`user`中指定id的数据                        |
      | getItemOfUserByUserName                | 查询表`user`中指定用户名的数据，用于展示当前用户信息            |
      | getItemCountOfUserByUserName           | 查询表`user`中当前用户名的数据条数，用于判定此用户名是否已被注册      |
      | getItemCountOfUserByUserNameAndUserPwd | 查询表`user`中当前用户名与用户密码的数据条数，用于判定用户登录是否成功   |
      | getItemIdOfUserByUserName              | 查询表`user`中指定用户名的数据的*id*，用于后续查询相应的保存的采集数据 |
      | insertItem2MonitorData                 | 向表`monitor_data`中插入新数据                   |
      | getAllSaveTimeOfMDByUserIdAndTimeRange | 查询表`monitor_data`中指定用户*id*与查询时间范围查询范围内的所有记录的时间 |
      | getItemCountOfMDByUserIdAndSaveTime    | 查询表`monitor_data`中指定用户*id*与保存的数据条数，用于判定插入成功 |
      | getAllDataOfMDByUserIdAndSaveTime      | 查询表`monitor_data`中指定用户*id*与保存时间的数据，用于历史记录展示 |

  * **`DataReceive`** — 基于蓝牙数据接收相关操作

    * **`DataReceiveService.java`** — 基于蓝牙的数据接收实现类。
    * **`OnGetDataListener.java`** — 采集到数据就的回调**接口**。

  * **`EditTextViewMode`** — 所有编辑框操作的`ViewModel`

    * **`UserViewModel.java`** — 用户信息编辑相关操作。

  * **`ListViewModel`** — 所有`List`相关的`ViewModel`

    * **`BtDeviceListItemViewModel.java`** — 用户响应点击所有发现蓝牙设备列表的操作。
    * **`ListItemViewUpdata.java`** — 蓝牙设备信息更新的回调**接口**。
    * **`NewProgressCreation.java`** — 用于在`Activity`中实现`List`单击时向数据接收界面的跳转操作的回调**接口**。
    * **`OnGetNewBundle.java`** — 返回构建新`Bundle`时的回调**接口**。
    * **`SearchHistoryListItemViewModel.java`** — 用户响应点击所有选定时间范围内历史记录列表的操作。

  * **`MethodsViewModel`** — 所有全局类方法

    * **`ActionBarOperation.java`** — 针对系统`ActionBar`的相关操作。

      | 方法名                | 备注                      |
      | ------------------ | ----------------------- |
      | setSystemActionBar | 配置系统`ActionBar`为指定背景色隐藏 |
      | getStatusBarHeight | 获取系统`ActionBar`的高度      |

    * **`BtPairing.java`** — 蓝牙设备配对相关操作。

      | 方法名        | 备注      |
      | ---------- | ------- |
      | creatBond  | 与蓝牙设备配对 |
      | removeBond | 与设备解除配对 |

    * **`EffectiveClick.java`** — 判定各种模式的有效点击操作。

      | 方法名                    | 备注         |
      | ---------------------- | ---------- |
      | isEffectiveDoubleClick | 是否为有效的两次单击 |

    * **`ExitOperation.java`** — 判定各种模式的退出操作。

      | 方法名          | 备注     |
      | ------------ | ------ |
      | exitBy2Click | 单击两次退出 |

    * **`HideSoftKeyBoard.java`** — 隐藏软键盘相关操作。

      | 方法名              | 备注                  |
      | ---------------- | ------------------- |
      | hideSoftKeyboard | 隐藏当前`Activity`下的软键盘 |

      ​

    * **`ImmersionLine.java`** — 设置系统导航栏相关操作。

      | 方法名           | 备注           |
      | ------------- | ------------ |
      | ImmersionLine | 设置系统状态栏为制定颜色 |

    * **`SystemBarTintManager.java`** — 自定义系统导航栏配置。