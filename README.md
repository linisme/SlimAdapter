
[ ![Download](https://api.bintray.com/packages/idik-net/SlimAdapter/SlimAdapter/images/download.svg) ](https://bintray.com/idik-net/SlimAdapter/SlimAdapter/_latestVersion)



# SlimAdapter
A slim &amp; clean &amp; typeable Adapter withoooooooout# VIEWHOLDER

# Features

* No ViewHolder any more
* fluent & simple API
* multi-typeable adapter

# Setup
```java
compile 'net.idik:slimadapter:1.0.0'
```

# Usages
```java        

SlimAdapter.create()
                .with(R.layout.item_user, new SlimInjector<User>() {
                    @Override
                    protected void onInject(User data, IViewInjector injector) {
                        injector.text(R.id.name, data.getName())
                                .text(R.id.age, String.valueOf(data.getAge()))
                                .textColor(R.id.age, Color.RED)
                                .textSize(R.id.age, 8);
                    }
                })
                .with(R.layout.item_interger, new SlimInjector<Integer>() {
                    @Override
                    protected void onInject(Integer data, IViewInjector injector) {
                        injector.text(R.id.text, data.toString());

                    }
                })
                .with(R.layout.item_string, new SlimInjector<String>() {
                    @Override
                    protected void onInject(String data, IViewInjector injector) {
                        injector.text(R.id.text, data);
                    }
                })
                .withDefault(R.layout.item_string, new SlimInjector() {
                    @Override
                    protected void onInject(Object data, IViewInjector injector) {
                        injector.text(R.id.text, data.toString())
                                .clicked(R.id.text, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(MainActivity.this, "DEFAULT INJECT", Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                })
                .attachTo(recyclerView)
                .setData(data)
                .notifyDataSetChanged();
    }
    
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
