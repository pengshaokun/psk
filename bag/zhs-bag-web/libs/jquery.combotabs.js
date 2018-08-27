(function ($) {
    function create(target) {
        var state = $.data(target, "combotabs");
        var opts = state.options;
        var tabs = state.tabs;
        $(target).addClass("combotabs-f");
        $(target).combo($.extend({}, opts, {
            onShowPanel: function () {
                opts.onShowPanel.call(this);
            }
        }));
        var panel = $(target).combo("panel");
        if (!tabs) {
            tabs = $("<div></div>").appendTo(panel);
            state.tabs = tabs;
        }
        tabs.tabs($.extend({}, opts, {
            onLoad: function (panel) {
            },
            onSelect: function (title, index) {
            }
        }));
        var value = $(target).combo("getValue");
        if (value) {
            addTab(value, null, "选择省", null, true);
        }
        else {
            addTab(null, null, "选择省", null, true);
        }
        function addTab(id, pid, title, index, selected) {
            var href = opts.href;
            var parmas = [];
            if (id) parmas.push('id=' + id);
            if (pid) parmas.push('pid=' + pid);
            if (parmas.length > 0) href += '?' + parmas.join('&');
            tabs.tabs('add', {
                id: id,
                title: title,
                href: href,
                selected: selected,
                index: index,
                loader: function (data, success, error) {
                    var _opts = $(this).panel("options");
                    if (!_opts.href) {
                        return false;
                    }
                    $.ajax({
                        type: _opts.method,
                        url: _opts.href,
                        cache: false,
                        success: function (data) {
                            success(data);
                        }
                    });
                },
                extractor: function (data) {
                    var content = $('<ul></ul>');
                    $.each(data, function (i, o) {
                        var li = $('<li>' + o.text + '</li>');
                        li.click(function () {
                            var selectedTab = tabs.tabs('getSelected');
                            var selectedIndex = tabs.tabs('getTabIndex', selectedTab);
                            var i = tabs.tabs('tabs').length - 1;
                            while (selectedIndex < i) {
                                tabs.tabs('close', i);
                                i--;
                            }
                            tabs.find('.tabs-selected .tabs-title').each(function () {
                                $(this).html(o.text);
                            });
                            if (o.state == 'closed') {
                                var title = o.text;
                                switch (selectedIndex) {
                                    case -1:
                                        title = "选择省份/自治区";
                                        break;
                                    case 0:
                                        title = "选择城市/地区";
                                        break;
                                    case 1:
                                        title = "选择区县";
                                        break;
                                    case 2:
                                        title = "选择乡镇";
                                        break;
                                    case 3:
                                        title = "选择居委会";
                                        break;
                                }
                                addTab(null, o.id, title, null, true);
                            }
                            else {
                                $(target).combo('setValue', o.id).combo('setText', o.attributes.fullName).combo('hidePanel');
                                opts.onSelect.call(target, o);
                            }
                        });
                        content.append(li);
                        if (id != null && id == o.id) {
                            if (selected) {
                                tabs.find('.tabs-selected .tabs-title ').each(function () {
                                    $(this).html(o.text);
                                });
                                $(target).combo('setText', o.attributes.fullName);
                                var ids = o.attributes.path.split(',');
                                var titles = o.attributes.fullName.split('/');
                                var level = ids.length - 1;
                                if (level > 1) {
                                    var i = 0;
                                    while (i < level) {
                                        addTab(ids[i], null, titles[i], i, false);
                                        i++;
                                    }
                                }
                            }
                        }
                    });
                    return content;
                },
            });
        }
    };

    $.fn.combotabs = function (options, param) {
        if (typeof options == "string") {
            var method = $.fn.combotabs.methods[options];
            if (method) {
                return method(this, param);
            } else {
                return this.combo(options, param);
            }
        }
        options = options || {};
        return this.each(function () {
            var state = $.data(this, "combotabs");
            if (state) {
                $.extend(state.options, options);
            } else {
                $.data(this, "combotabs", {
                    options: $.extend({}, $.fn.combotabs.defaults, $.fn.combotabs.parseOptions(this), options)
                });
            }
            create(this);
        });
    };
    $.fn.combotabs.parseOptions = function (target) {
        return $.extend({}, $.fn.combo.parseOptions(target), $.fn.tabs.parseOptions(target));
    };
    $.fn.combotabs.defaults = $.extend({}, $.fn.combo.defaults, $.fn.tabs.defaults, {
        editable: false,
        textField: null,
        unselectedValues: [],
        mappingRows: [],
        method: "get",
        href: null,
        onSelect: function (node) {
        }
    });
})(jQuery);
