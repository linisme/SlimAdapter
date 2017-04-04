![logo](./slimadapter-logo.png)


[ ![Download](https://api.bintray.com/packages/idik-net/SlimAdapter/SlimAdapter/images/download.svg) ](https://bintray.com/idik-net/SlimAdapter/SlimAdapter/_latestVersion)



# SlimAdapter
A slim &amp; clean &amp; typeable Adapter withoooooooout# VIEWHOLDER

# Features

* No ViewHolder any more
* fluent & simple API
* multi-typeable adapter

# Setup
```java
compile 'net.idik:slimadapter:1.1.0'
```

# Usages

## Java

1. Create SlimAdapter & attachTo target RecyclerView

    * use methed with(layoutRes, SlimInjector<DataType>)
 
    * use methed withDefault(layoutRes, SlimInjector)

```java        

 SlimAdapter.create()
                .with(R.layout.item_user, new SlimInjector<User>() {
                    @Override
                    protected void onInject(User data, IViewInjector injector) {
                        ...// inject data into views
                    }
                })
                .with(R.layout.item_interger, new SlimInjector<Integer>() {
                    @Override
                    protected void onInject(Integer data, IViewInjector injector) {
                        ...// inject data into views
                    }
                })
                .with(R.layout.item_string, new SlimInjector<String>() {
                    @Override
                    protected void onInject(String data, IViewInjector injector) {
                        ...// inject data into views
                    }
                })
                .withDefault(R.layout.item_string, new SlimInjector() {
                    @Override
                    protected void onInject(Object data, IViewInjector injector) {
                        ...// inject data into views
                    }
                })
                .attachTo(recyclerView);
    }
    
```


1. Inject data into views with fluent apis

```java
injector.text(R.id.name, data.getName())
        .text(R.id.age, String.valueOf(data.getAge()))
        .textColor(R.id.age, Color.RED)
        .textSize(R.id.age, 8)
        .longClicked(R.id.name, new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        //do stuff...
                                        return false;
                                    }
                                })
        .clicked(R.id.text, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //do stuff...
                                    }
                                })
        .with(R.id.name, new IViewInjector.Action<TextView>() {
                                    @Override
                                    public void action(TextView view) {
                                        //do stuff...
                                    }
                                })
        ...;
```

1. use SlimAdapter as normal adapter


## SlimAdapter 💗 Kotlim

```Kotlin
SlimAdapter.create()
                .with<String>(R.layout.item_string) { data, injector ->
                    ...// inject data into views
                }
                .with<User>(R.layout.item_user) { data, injector ->
                    ...// inject data into views
                }
                .with<Int>(R.layout.item_interger) { data, injector ->
                    ...// inject data into views
                }
                .withDefault(R.layout.item_string) { data, injector ->
                    ...// inject data into views
                }
                .attachTo(recyclerView)
```


--------------

<a href='https://bintray.com/idik-net/SlimAdapter/SlimAdapter?source=watch' alt='Get automatic notifications about new "SlimAdapter" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>




# License

    MIT License

    Copyright (c) 2017 认真的帅斌

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
