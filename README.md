# Java Servlet Develop Startup with VSCode

## Software installation and Windows Setting

### 1. Install JDK
- Download jdk-17_windows-x64_bin.zip
https://www.oracle.com/java/technologies/downloads/#java17
- Unzip to  D:\install\Java\jdk-17
- Windows setting
    - Right click Windows icon > System > Advanced Settings > Environment Variables... 
        - System variables : Add JAVA_HOME , with content "D:\install\Java\jdk-17"
        - User variables : select "PATH" > Edit > Add %JAVA_HOME%\bin
- testing : Open a Terminal , run command : java -version 可以執行表示安裝及路徑正確

### 2. Install MAVEN
- Download apache-maven-3.9.1-bin.zip
https://maven.apache.org/download.cgi
- Unzip to D:\install\apache-maven-3.9.0
- Windows setting
    - 系統 > 進階系統設定 > 環境變數... 
        - 系統變數 : Add MAVEN_HOME , with content "D:\install\apache-maven-3.9.0"
        - 使用者變數 : PATH > Edit : Add %MAVEN_HOME%\bin
- 測試 : 開啟 Terminal , 執行 mvn -version 可以執行表示安裝及路徑正確

### 3. 安裝 Tomcat (Servlet 專案才需要)
- 下載 apache-tomcat-9.0.73.zip
https://tomcat.apache.org/
- 解壓縮安裝後產生tomcat目錄 D:\install\Tomcat\apache-tomcat-9.0.73


## VS Code 設定

### 1. 安裝 VSCode Portable
- 下載 VSCode-win32-x64-1.76.2.zip
    - https://code.visualstudio.com/Download
- 解壓縮到 D:\VSCodePortable
- 在 D:\VSCodePortable 下建立 data/ 目錄, 完成

### 2. 安裝 Extension
- Community Server Connectors
- Extension Pack for Java
- 尋找 doggy8088, 安裝 "Essential Java Spring Boot Snippets" (Will保哥 發行)

### 4. JAVA Settings (File -> Prefrences -> Settings)
- Java › Debug › Settings: Hot Code Replace
    - 設成 auto : 可以讓 compile 速度變快, debug時可以直接改code, 就可以繼續 debug, 不需要重新執行
- Editor: Folding Imports By Default
    - 勾選 : 程式開頭的 import 語法會縮起來不展開, 有利程式閱讀
- Java › Save Actions: Organize Imports
    - 勾選 : 可以在開發時不用先寫 import 語法, 在存檔時會自動 import packages
- Maven › Executable: Path
    - D:\install\apache-maven-3.9.0\bin\mvn
- Java › Configuration › Maven: Global Settings
    - D:\install\apache-maven-3.9.0\conf\setting.xml

### 5. MAVEN Settings (File -> Prefrences -> Settings)
- Maven › Executable: Path
D:\install\apache-maven-3.9.0\bin\mvn
- Java › Configuration › Maven: Global Settings
D:\install\apache-maven-3.9.0\conf\setting.xml

## 建立新專案
- Java Help Center(Shift + F1)
    -> Create a New Project, 
    -> No build tools 
    -> 選擇 project 目錄, 例如 Hello
    -> 輸入 project name, 例如 demo1 會建立以下目錄結構
    - Hello/demo1/.vscode : vscode 設定檔 .json 的目錄
    - Hello/demo1/lib : 參考函式庫 .jar 的目錄
    - Hello/demo1/src : 程式碼 .java 的目錄
    - Hello/demo1/bin : 編譯結果 .class 的目錄
構
- settings.json : 觀察 sourcePaths, outputPath, referencedLibraries 的設定是否正確
- App.java : 自動產生的 Hello World程式, 第一次開啟會自動 compile


## Servlet 專案設定
### 1. 建立 Servlet 專案
- Command(Ctrl+Shift+P) : 
    - Java : Create Java Project ...
    -> Maven 
    -> maven-archetype-webapp
    -> Version : 1.4
    -> group id : com.example
    -> artifact id : demo
- Select Destnation Folder , the demo/ folder will be created in it.
- Define value for property 'version' 1.0-SNAPSHOT: : [Enter]
- Confirm properties configuration: Y
- 或是點選 MAVEN Section 的 "+" 也可以增加 Servlet 專案

### 2. 修改 pom.xml
- 依據 JDK 版本修改 <maven.compiler.source> <maven.compiler.target>, JDK 17 設成 17 , JDK 18 設成 18
```
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>
```

### 3. 加入 dependency
- 展開 MAVEN Section > demo Maven WebApp > Dependencies > 點 + > 輸入 javax.servlet > 選擇 javax.servlet-api
- 檢視 pom.xml 會自動增加 javax.servlet dependency
```
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
    </dependency>
```
### 4. Compile
- 展開 MAVEN Section > demo Maven Webapp 按右鍵 > Run Maven Commands... > compile 確認編譯沒問題

### 5. 設定 Tomcat
- 展開 SERVERS Section > Community Server Connector 按右鍵 > Start RSP Provider
- 展開 SERVERS Section > Community Server Connector 按右鍵 > Create New Server... > No, use server on disk > 選擇 tomcat 路徑 D:\install\Tomcat\apache-tomcat-9.0.73
- 出現視窗 New Server: Tomcat 9.x > Finish
- 展開 SERVERS Section > Community Server Connector > Tomcat 9.x 按右鍵 > Start Server
- 瀏覽 http://localhost:8080/ 可以成功瀏覽表示成功

### 6. 執行 Servlet
- 展開 MAVEN Section > demo web app 按右鍵 > Run Maven Commands... > package 打包程式, 會產生檔案demo\target\demo.war
- 展開 Servers > Tomcat 9.x 按右鍵 > Add Deployment > 點 File > 選擇 demo.war 佈署程式 > Parameter 選 No
- 展開 Servers > Tomcat 9.x 按右鍵 > Publish Server (Full)
- 開啟瀏覽器 > 輸入 http://localhost:8080/demo/ 確認可以成功執行

### 7. 新增 JAVA file
- 在 src/main/ 下新增目錄 java/
- Java Projects 區塊 > 打開 demo > 點 src/main/java 右邊的 + > 輸入class名稱 com.example.HelloServlet > 會在對應目錄產生 HelloServlet.java

### 7. 修改程式後重新執行 Servlet
- 展開 MAVEN > demo web app 按右鍵 > Run Maven Commands... > deploy 
- 開啟瀏覽器 > 輸入 http://localhost:8080/demo/ 確認可以成功執行

## Java Projects 設定
- 建立 Java 專案後會在側邊欄的 Explore 出現
- 可以拖拉到最左側快捷欄
- Configure Java Runtime 可以修改 JDK 使用版本
- Configure Classpath 設定 java 目錄
    - Select the project folder : 選擇 PROJECT_FOLDER
    - Sources : 設定為 src
    - Output : 設定為 bin
    - Referenced Libraries : 設定外部 .jar 路徑
- 建立新類別
    - 按 src/ 旁邊的 + , 可以建立新類別
- 建立 package
    - src/ 按右鍵 -> New package
    - package名稱為了避免重覆, 命名上通常會把公司網址倒過來, 例如 com.cht.wjchu.pkg1
    - 會在 src/ 下自動建立 package 的階層目錄

## Trouble Shutting
### 1. 解決big5編碼的程式碼, 編譯失敗的問題
- Windows 設定
    - Windows Search : region
    -> 設定地區格式 
    -> 其他日期時間與地區設定
    -> 變更日期時間或數字格式
    -> 系統管理 "非Unicode程式的語言"
    -> 變更系統地區設定
    -> 選擇 "中文(繁體, 台灣)"
- VScode 設定
    - Settings 
    -> 勾選 Files: Auto Guess Encoding
    -> Files: Encoding -> 選 Traditional Chinese(Big5)
    - 把顯示亂碼的 .java 關閉再開啟, 就可以正確顯示成中文
    - Compile 程式碼, 會失敗
    - 此時點選右下角的 fix -> Clean workspace cache -> Restart and delete
    - Project 會重開 -> 點 Rebuild All -> 可以編譯成功
    - 選 Run 確認可以正確執行

- 最好還是以 UTF-8 編碼儲存, 才是根本解決之道
