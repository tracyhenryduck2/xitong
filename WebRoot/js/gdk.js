
/**
 * gdk.js
 * version 1.1.1 2010/06/18
 */
jQuery.cookie = function (a, b, c) {
	if (typeof b != "undefined") {
		c = c || {};
		if (b === null) {
			b = "";
			c.expires = -1;
		}
		var d = "";
		if (c.expires && (typeof c.expires == "number" || c.expires.toUTCString)) {
			if (typeof c.expires == "number") {
				d = new Date;
				d.setTime(d.getTime() + c.expires * 24 * 60 * 60 * 1000);
			} else {
				d = c.expires;
			}
			d = "; expires=" + d.toUTCString();
		}
		var e = c.path ? "; path=" + c.path : "", f = c.domain ? "; domain=" + c.domain : "";
		c = c.secure ? "; secure" : "";
		document.cookie = [a, "=", encodeURIComponent(b), d, e, f, c].join("");
	} else {
		b = null;
		if (document.cookie && document.cookie != "") {
			c = document.cookie.split(";");
			for (d = 0; d < c.length; d++) {
				e = jQuery.trim(c[d]);
				if (e.substring(0, a.length + 1) == a + "=") {
					b = decodeURIComponent(e.substring(a.length + 1));
					break;
				}
			}
		}
		return b;
	}
};
(function (a) {
	function b(d) {
		var e = [].slice.call(arguments, 1), f = 0;
		d = a.event.fix(d || window.event);
		d.type = "mousewheel";
		if (d.wheelDelta) {
			f = d.wheelDelta / 120;
		}
		if (d.detail) {
			f = -d.detail / 3;
		}
		e.unshift(d, f);
		return a.event.handle.apply(this, e);
	}
	var c = ["DOMMouseScroll", "mousewheel"];
	a.event.special.mousewheel = {setup:function () {
		if (this.addEventListener) {
			for (var d = c.length; d; ) {
				this.addEventListener(c[--d], b, false);
			}
		} else {
			this.onmousewheel = b;
		}
	}, teardown:function () {
		if (this.removeEventListener) {
			for (var d = c.length; d; ) {
				this.removeEventListener(c[--d], b, false);
			}
		} else {
			this.onmousewheel = null;
		}
	}};
	a.fn.extend({mousewheel:function (d) {
		return d ? this.bind("mousewheel", d) : this.trigger("mousewheel");
	}, unmousewheel:function (d) {
		return this.unbind("mousewheel", d);
	}});
})(jQuery);
var $browser = {};
$browser.nav_ua = navigator.userAgent.toLowerCase();
$browser.ua_check = function (a) {
	return a.test(this.nav_ua);
};
$browser.isStrict = document.compatMode == "CSS1Compat";
$browser.isOpera = $browser.ua_check(/opera/);
$browser.isChrome = $browser.ua_check(/chrome/);
$browser.isWebKit = $browser.ua_check(/webkit/);
$browser.isSafari = !$browser.isChrome && $browser.ua_check(/safari/);
$browser.isSafari3 = $browser.isSafari && $browser.ua_check(/version\/3/);
$browser.isSafari4 = $browser.isSafari && $browser.ua_check(/version\/4/);
$browser.isIE = !$browser.isOpera && $browser.ua_check(/msie/);
$browser.isIE6 = $browser.isIE && $browser.ua_check(/msie 6/);
$browser.isIE7 = $browser.isIE && $browser.ua_check(/msie 7/);
$browser.isIE8 = $browser.isIE && $browser.ua_check(/msie 8/);
$browser.isGecko = !$browser.isWebKit && $browser.ua_check(/gecko/);
$browser.isGecko3 = $browser.isGecko && $browser.ua_check(/rv:1\.9/);
$browser.isFirefox = $browser.ua_check(/firefox/);
$browser.isFirefox3 = $browser.ua_check(/firefox\/3/);
(function (a) {
	a.extend(a.fn, {iframeReady:function (b) {
		$browser.isIE ? a(this).bind("readystatechange", function () {
			this.readyState == "complete" && b && typeof b == "function" && b(this);
		}) : a(this).bind("load", function () {
			b && typeof b == "function" && b(this);
		});
	}});
})(jQuery);
String.prototype.replaceAll = function (a, b) {
	return this.replace(new RegExp(a, "gm"), b);
};
String.prototype.trim = function () {
	return this.replace(/(^s*)|(s*$)/g, "");
};
String.prototype.startWith = function (a) {
	return (new RegExp("$" + a.toString())).test(this);
};
String.prototype.endWith = function (a) {
	return (new RegExp(a.toString() + "$")).test(this);
};
function toUTF8(a) {
	var b, c, d = b = "";
	for (c = 0; c < a.length; c++) {
		b = a.charCodeAt(c);
		if (b & 65408) {
			b = b & 61440 ? "%" + (b >> 12 | 224).toString(16) + "%" + (b >> 6 & 63 | 128).toString(16) + "%" + (b & 63 | 128).toString(16) : "%" + (b >> 6 | 192).toString(16) + "%" + (b & 63 | 128).toString(16);
			d += b;
		} else {
			d += a.charAt(c);
		}
	}
	return d;
}
function filterInt(a) {
	a = a || window.event;
	a = a.keyCode || a.which;
	return a >= 48 && a <= 57 || a == 8 || a == 37 || a == 39 || a == 44 || a == 45 || a == 46;
}
function filterFloat(a) {
	var b = a || window.event;
	a = b.keyCode || b.which;
	b = b.srcElement || b.target;
	if (a == 46) {
		b = b.value;
		if (b == "" || b.split(".").length == 2) {
			return false;
		}
	}
	return a >= 48 && a <= 57 || a == 8 || a == 37 || a == 39 || a == 44 || a == 45 || a == 46;
}
function checkAll(a, b) {
	$(":checkbox[name=" + b + "]").each(function () {
		$(this).attr("checked", a.checked);
	});
}
function getCheckedNum(a) {
	return $(":checkbox[name='" + a + "']:checked").length;
}
function getCheckedValue(a) {
	var b = "", c = getCheckedNum(a);
	c > 0 && $(":checkbox[name='" + a + "']:checked").each(function (d) {
		b += $(this).val();
		if (d != c - 1) {
			b += ",";
		}
	});
	return b;
}
function isCheckAny(a) {
	return getCheckedNum(a) > 0;
}
function isCheckOne(a) {
	return getCheckedNum(a) == 1;
}
function randomString(a) {
	for (var b = "", c, d = 0; d < a; d++) {
		c = Math.floor(Math.random() * 62);
		b += "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(c);
	}
	return b;
}
function highlight(a, b, c) {
	c = c ? c.replace(/(<(\w+)(\s+[^<]*)*>)([^<]*)(<\/\2>)/g, "$1$$1$5") : "<b style='color:#ff0000;'>$1</b>";
	b = b.trim();
	return a.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + b + ")(?![^<>]*>)(?![^&;]+;)", "gi"), c);
}
function renderField() {
	$(":text.GF-field").addClass("GF-field").focus(function () {
		$(this).addClass("GF-field-focus");
	}).blur(function () {
		$(this).removeClass("GF-field-focus");
	});
	$(":password.GF-field").addClass("GF-field").focus(function () {
		$(this).addClass("GF-field-focus");
	}).blur(function () {
		$(this).removeClass("GF-field-focus");
	});
	$("textarea").focus(function () {
		$(this).addClass("GF-area-focus");
	}).blur(function () {
		$(this).removeClass("GF-area-focus");
	});
	$(":file.GF-field").addClass("GF-field").focus(function () {
		$(this).addClass("GF-field-focus");
	}).blur(function () {
		$(this).removeClass("GF-field-focus");
	});
}
function renderButton() {
	$(":submit.GF-btn").addClass("GF-btn").hover(function () {
		$(this).addClass("GF-btn-hover");
	}, function () {
		$(this).removeClass("GF-btn-hover");
	});
	$(":reset.GF-btn").addClass("GF-btn").hover(function () {
		$(this).addClass("GF-btn-hover");
	}, function () {
		$(this).removeClass("GF-btn-hover");
	});
	$(":button.GF-btn").addClass("GF-btn").hover(function () {
		$(this).addClass("GF-btn-hover");
	}, function () {
		$(this).removeClass("GF-btn-hover");
	});
	$("button.GF-btn").addClass("GF-btn").hover(function () {
		$(this).addClass("GF-btn-hover");
	}, function () {
		$(this).removeClass("GF-btn-hover");
	});
}
$(function () {
	renderField();
	renderButton();
	//在页面上引入以下代码，然后给相关节点加上id属性，当name属性相同时候会以id属性来验证
	//http://xinklabi.iteye.com/blog/2128781
	if ($.validator) { //input name 名称相同时不只校验第一个的解决版本
		//fix: when several input elements shares the same name, but has different id-ies....
		$.validator.prototype.elements = function () {
			var validator = this,
				rulesCache = {};
			// select all valid inputs inside the form (no submit or reset buttons)
			// workaround $Query([]).add until http://dev.jquery.com/ticket/2114 is solved
			return $([]).add(this.currentForm.elements)
			.filter(":input")
			.not(":submit, :reset, :image, [disabled]")
			.not(this.settings.ignore)
			.filter(function () {
				var elementIdentification = this.id || this.name;
				!elementIdentification && validator.settings.debug && window.console && console.error("%o has no id nor name assigned", this);
				// select only the first element for each name, and only those with rules specified
				if (elementIdentification in rulesCache || !validator.objectLength($(this).rules()))
					return false;
				rulesCache[elementIdentification] = true;
				return true;
			});
		};
	}
});
(function (a) {
	a.extend({metadata:{defaults:{type:"class", name:"metadata", cre:/({.*})/, single:"metadata"}, setType:function (b, c) {
		this.defaults.type = b;
		this.defaults.name = c;
	}, get:function (b, c) {
		var d = a.extend({}, this.defaults, c);
		if (!d.single.length) {
			d.single = "metadata";
		}
		var e = a.data(b, d.single);
		if (e) {
			return e;
		}
		e = "{}";
		if (d.type == "class") {
			var f = d.cre.exec(b.className);
			if (f) {
				e = f[1];
			}
		} else {
			if (d.type == "elem") {
				if (!b.getElementsByTagName) {
					return;
				}
				f = b.getElementsByTagName(d.name);
				if (f.length) {
					e = a.trim(f[0].innerHTML);
				}
			} else {
				if (b.getAttribute != undefined) {
					if (f = b.getAttribute(d.name)) {
						e = f;
					}
				}
			}
		}
		if (e.indexOf("{") < 0) {
			e = "{" + e + "}";
		}
		e = eval("(" + e + ")");
		a.data(b, d.single, e);
		return e;
	}}});
	a.fn.metadata = function (b) {
		return a.metadata.get(this[0], b);
	};
	a.extend(a.fn, {validate:function (b) {
		if (this.length) {
			var c = a.data(this[0], "validator");
			if (c) {
				return c;
			}
			c = new a.validator(b, this[0]);
			a.data(this[0], "validator", c);
			if (c.settings.onsubmit) {
				this.find("input, button").filter(".cancel").click(function () {
					c.cancelSubmit = true;
				});
				c.settings.submitHandler && this.find("input, button").filter(":submit").click(function () {
					c.submitButton = this;
				});
				this.submit(function (d) {
					function e() {
						if (c.settings.submitHandler) {
							if (c.submitButton) {
								var f = a("<input type='hidden'/>").attr("name", c.submitButton.name).val(c.submitButton.value).appendTo(c.currentForm);
							}
							c.settings.submitHandler.call(c, c.currentForm);
							c.submitButton && f.remove();
							return false;
						}
						return true;
					}
					c.settings.debug && d.preventDefault();
					if (c.cancelSubmit) {
						c.cancelSubmit = false;
						return e();
					}
					if (c.form()) {
						if (c.pendingRequest) {
							c.formSubmitted = true;
							return false;
						}
						return e();
					} else {
						c.focusInvalid();
						return false;
					}
				});
			}
			return c;
		} else {
			b && b.debug && window.console && console.warn("nothing selected, can't validate, returning nothing");
		}
	}, valid:function () {
		if (a(this[0]).is("form")) {
			return this.validate().form();
		} else {
			var b = true, c = a(this[0].form).validate();
			this.each(function () {
				b &= c.element(this);
			});
			return b;
		}
	}, removeAttrs:function (b) {
		var c = {}, d = this;
		a.each(b.split(/\s/), function (e, f) {
			c[f] = d.attr(f);
			d.removeAttr(f);
		});
		return c;
	}, rules:function (b, c) {
		var d = this[0];
		if (b) {
			var e = a.data(d.form, "validator").settings, f = e.rules, j = a.validator.staticRules(d);
			switch (b) {
			  case "add":
				a.extend(j, a.validator.normalizeRule(c));
				f[d.name] = j;
				if (c.messages) {
					e.messages[d.name] = a.extend(e.messages[d.name], c.messages);
				}
				break;
			  case "remove":
				if (!c) {
					delete f[d.name];
					return j;
				}
				var n = {};
				a.each(c.split(/\s/), function (r, p) {
					n[p] = j[p];
					delete j[p];
				});
				return n;
			}
		}
		d = a.validator.normalizeRules(a.extend({}, a.validator.metadataRules(d), a.validator.classRules(d), a.validator.attributeRules(d), a.validator.staticRules(d)), d);
		if (d.required) {
			e = d.required;
			delete d.required;
			d = a.extend({required:e}, d);
		}
		return d;
	}});
	a.extend(a.expr[":"], {blank:function (b) {
		return !a.trim("" + b.value);
	}, filled:function (b) {
		return !!a.trim("" + b.value);
	}, unchecked:function (b) {
		return !b.checked;
	}});
	a.validator = function (b, c) {
		this.settings = a.extend({}, a.validator.defaults, b);
		this.currentForm = c;
		this.init();
	};
	a.validator.format = function (b, c) {
		if (arguments.length == 1) {
			return function () {
				var d = a.makeArray(arguments);
				d.unshift(b);
				return a.validator.format.apply(this, d);
			};
		}
		if (arguments.length > 2 && c.constructor != Array) {
			c = a.makeArray(arguments).slice(1);
		}
		if (c.constructor != Array) {
			c = [c];
		}
		a.each(c, function (d, e) {
			b = b.replace(new RegExp("\\{" + d + "\\}", "g"), e);
		});
		return b;
	};
	a.extend(a.validator, {defaults:{messages:{}, groups:{}, rules:{}, errorClass:"fv-error", validClass:"fv-valid", errorElement:"label", showFormTip:true, focusInvalid:true, errorContainer:a([]), errorLabelContainer:a([]), onsubmit:true, ignore:[], ignoreTitle:false, onfocusin:function (b) {
		this.lastActive = b;
		if (this.settings.focusCleanup && !this.blockFocusCleanup) {
			this.settings.unhighlight && this.settings.unhighlight.call(this, b, this.settings.errorClass, this.settings.validClass);
			this.errorsFor(b).hide();
		}
	}, onfocusout:function (b) {
		if (!this.checkable(b) && (b.name in this.submitted || !this.optional(b))) {
			this.element(b);
		}
	}, onkeyup:function (b) {
		if (b.name in this.submitted || b == this.lastElement) {
			this.element(b);
		}
	}, onclick:function (b) {
		if (b.name in this.submitted) {
			this.element(b);
		} else {
			b.parentNode.name in this.submitted && this.element(b.parentNode);
		}
	}, highlight:function (b, c, d) {
		a(b).addClass(c).removeClass(d);
	}, unhighlight:function (b, c, d) {
		a(b).removeClass(c).addClass(d);
	}}, setDefaults:function (b) {
		a.extend(a.validator.defaults, b);
	}, messages:{required:"This field is required.", remote:"Please fix this field.", email:"Please enter a valid email address.", url:"Please enter a valid URL.", date:"Please enter a valid date.", dateISO:"Please enter a valid date (ISO).", number:"Please enter a valid number.", digits:"Please enter only digits.", creditcard:"Please enter a valid credit card number.", equalTo:"Please enter the same value again.", accept:"Please enter a value with a valid extension.", maxlength:a.validator.format("Please enter no more than {0} characters."), minlength:a.validator.format("Please enter at least {0} characters."), rangelength:a.validator.format("Please enter a value between {0} and {1} characters long."), range:a.validator.format("Please enter a value between {0} and {1}."), max:a.validator.format("Please enter a value less than or equal to {0}."), min:a.validator.format("Please enter a value greater than or equal to {0}.")}, autoCreateRanges:false, prototype:{init:function () {
		function b(e) {
			var f = a.data(this[0].form, "validator");
			f.settings["on" + e.type] && f.settings["on" + e.type].call(f, this[0]);
		}
		this.labelContainer = a(this.settings.errorLabelContainer);
		this.errorContext = this.labelContainer.length && this.labelContainer || a(this.currentForm);
		this.containers = a(this.settings.errorContainer).add(this.settings.errorLabelContainer);
		this.submitted = {};
		this.valueCache = {};
		this.pendingRequest = 0;
		this.pending = {};
		this.invalid = {};
		this.reset();
		var c = this.groups = {};
		a.each(this.settings.groups, function (e, f) {
			a.each(f.split(/\s/), function (j, n) {
				c[n] = e;
			});
		});
		var d = this.settings.rules;
		a.each(d, function (e, f) {
			d[e] = a.validator.normalizeRule(f);
		});
		a(this.currentForm).delegate("focusin focusout keyup", ":text, :password, :file, select, textarea", b).delegate("click", ":radio, :checkbox, select, option", b);
		this.settings.invalidHandler && a(this.currentForm).bind("invalid-form.validate", this.settings.invalidHandler);
	}, form:function () {
		this.checkForm();
		a.extend(this.submitted, this.errorMap);
		this.invalid = a.extend({}, this.errorMap);
		this.valid() || a(this.currentForm).triggerHandler("invalid-form", [this]);
		this.showErrors();
		return this.valid();
	}, checkForm:function () {
		this.prepareForm();
		for (var b = 0, c = this.currentElements = this.elements(); c[b]; b++) {
			this.check(c[b]);
		}
		return this.valid();
	}, element:function (b) {
		this.lastElement = b = this.clean(b);
		this.prepareElement(b);
		this.currentElements = a(b);
		var c = this.check(b);
		if (c) {
			delete this.invalid[b.name];
		} else {
			this.invalid[b.name] = true;
		}
		if (!this.numberOfInvalids()) {
			this.toHide = this.toHide.add(this.containers);
		}
		this.showErrors();
		return c;
	}, showErrors:function (b) {
		if (b) {
			a.extend(this.errorMap, b);
			this.errorList = [];
			for (var c in b) {
				this.errorList.push({message:b[c], element:this.findByName(c)[0]});
			}
			this.successList = a.grep(this.successList, function (d) {
				return !(d.name in b);
			});
		}
		this.settings.showErrors ? this.settings.showErrors.call(this, this.errorMap, this.errorList) : this.defaultShowErrors();
	}, resetForm:function () {
		a.fn.resetForm && a(this.currentForm).resetForm();
		this.submitted = {};
		this.prepareForm();
		this.hideErrors();
		this.elements().removeClass(this.settings.errorClass);
	}, numberOfInvalids:function () {
		return this.objectLength(this.invalid);
	}, objectLength:function (b) {
		var c = 0;
		for (var d in b) {
			c++;
		}
		return c;
	}, hideErrors:function () {
		this.addWrapper(this.toHide).hide();
		this.settings.showFormTip && this.hideFormTip();
	}, valid:function () {
		return this.size() == 0;
	}, size:function () {
		return this.errorList.length;
	}, focusInvalid:function () {
		if (this.settings.focusInvalid) {
			try {
				a(this.findLastActive() || this.errorList.length && this.errorList[0].element || []).filter(":visible").focus();
			}
			catch (b) {
			}
		}
	}, findLastActive:function () {
		var b = this.lastActive;
		return b && a.grep(this.errorList, function (c) {
			return c.element.name == b.name;
		}).length == 1 && b;
	}, elements:function () {
		var b = this, c = {};
		return a([]).add(this.currentForm.elements).filter(":input").not(":submit, :reset, :image, [disabled]").not(this.settings.ignore).filter(function () {
			!this.name && b.settings.debug && window.console && console.error("%o has no name assigned", this);
			if (this.name in c || !b.objectLength(a(this).rules())) {
				return false;
			}
			return c[this.name] = true;
		});
	}, clean:function (b) {
		return a(b)[0];
	}, errors:function () {
		return a(this.settings.errorElement + "." + this.settings.errorClass, this.errorContext);
	}, reset:function () {
		this.successList = [];
		this.errorList = [];
		this.errorMap = {};
		this.toShow = a([]);
		this.toHide = a([]);
		this.currentElements = a([]);
	}, prepareForm:function () {
		this.reset();
		this.toHide = this.errors().add(this.containers);
	}, prepareElement:function (b) {
		this.reset();
		this.toHide = this.errorsFor(b);
	}, check:function (b) {
		b = this.clean(b);
		if (this.checkable(b)) {
			b = this.findByName(b.name)[0];
		}
		var c = a(b).rules(), d = false;
		for (method in c) {
			var e = {method:method, parameters:c[method]};
			try {
				var f = a.validator.methods[method].call(this, b.value.replace(/\r/g, ""), b, e.parameters);
				if (f == "dependency-mismatch") {
					d = true;
				} else {
					d = false;
					if (f == "pending") {
						this.toHide = this.toHide.not(this.errorsFor(b));
						return;
					}
					if (!f) {
						this.formatAndAdd(b, e);
						return false;
					}
				}
			}
			catch (j) {
				this.settings.debug && window.console && console.log("exception occured when checking element " + b.id + ", check the '" + e.method + "' method", j);
				throw j;
			}
		}
		if (!d) {
			this.objectLength(c) && this.successList.push(b);
			return true;
		}
	}, customMetaMessage:function (b, c) {
		if (a.metadata) {
			var d = this.settings.meta ? a(b).metadata()[this.settings.meta] : a(b).metadata();
			return d && d.messages && d.messages[c];
		}
	}, customMessage:function (b, c) {
		var d = this.settings.messages[b];
		return d && (d.constructor == String ? d : d[c]);
	}, findDefined:function () {
		for (var b = 0; b < arguments.length; b++) {
			if (arguments[b] !== undefined) {
				return arguments[b];
			}
		}
	}, defaultMessage:function (b, c) {
		return this.findDefined(this.customMessage(b.name, c), this.customMetaMessage(b, c), !this.settings.ignoreTitle && b.title || undefined, a.validator.messages[c], "<strong>Warning: No message defined for " + b.name + "</strong>");
	}, formatAndAdd:function (b, c) {
		var d = this.defaultMessage(b, c.method), e = /\$?\{(\d+)\}/g;
		if (typeof d == "function") {
			d = d.call(this, c.parameters, b);
		} else {
			if (e.test(d)) {
				d = jQuery.format(d.replace(e, "{$1}"), c.parameters);
			}
		}
		this.errorList.push({message:d, element:b});
		this.errorMap[b.name] = d;
		this.submitted[b.name] = d;
	}, addWrapper:function (b) {
		if (this.settings.wrapper) {
			b = b.add(b.parent(this.settings.wrapper));
		}
		return b;
	}, defaultShowErrors:function () {
		for (var b = 0; this.errorList[b]; b++) {
			var c = this.errorList[b];
			this.settings.highlight && this.settings.highlight.call(this, c.element, this.settings.errorClass, this.settings.validClass);
			this.showLabel(c.element, c.message);
		}
		if (this.errorList.length) {
			this.toShow = this.toShow.add(this.containers);
		}
		if (this.settings.success) {
			for (b = 0; this.successList[b]; b++) {
				this.showLabel(this.successList[b]);
			}
		}
		if (this.settings.unhighlight) {
			b = 0;
			for (c = this.validElements(); c[b]; b++) {
				this.settings.unhighlight.call(this, c[b], this.settings.errorClass, this.settings.validClass);
			}
		}
		this.toHide = this.toHide.not(this.toShow);
		this.hideErrors();
		this.addWrapper(this.toShow).show();
	}, validElements:function () {
		return this.currentElements.not(this.invalidElements());
	}, invalidElements:function () {
		return a(this.errorList).map(function () {
			return this.element;
		});
	}, showLabel:function (b, c) {
		var d = this.errorsFor(b);
		if (d.length) {
			d.removeClass().addClass(this.settings.errorClass);
			d.attr("generated") && d.html(c);
			this.settings.showFormTip && d.html("");
		} else {
			d = a("<" + this.settings.errorElement + "/>").attr({"for":this.idOrName(b), generated:true}).addClass(this.settings.errorClass).html(c || "");
			this.settings.showFormTip && d.html("");
			if (this.settings.wrapper) {
				d = d.hide().show().wrap("<" + this.settings.wrapper + "/>").parent();
			}
			if (!this.labelContainer.append(d).length) {
				if (this.settings.errorPlacement) {
					this.settings.errorPlacement(d, a(b));
				} else {
					if (this.checkable(b)) {
						var e = a(":" + b.type + "[name='" + b.name + "']:last");
						d.appendTo(e.parent());
						e = null;
					} else {
						d.insertAfter(b);
					}
				}
			}
			this.settings.showFormTip && this.createFormTip();
		}
		if (this.settings.showFormTip) {
			var f = this;
			a(b).hover(function () {
				f.showFormTip(d, b, c);
			}, function () {
				f.hideFormTip();
			});
			a(d).hover(function () {
				f.showFormTip(d, b, c);
			}, function () {
				f.hideFormTip();
			});
		}
		if (!c && this.settings.success) {
			d.text("");
			typeof this.settings.success == "string" ? d.addClass(this.settings.success) : this.settings.success(d);
		}
		this.toShow = this.toShow.add(d);
	}, createFormTip:function () {
		if (!document.getElementById("_formValidateTip")) {
			var b = a("<div/>");
			a(document.body).append(b);
			b.attr("id", "_formValidateTip").addClass("form-validate-tip tip-left");
			b.html("<div class=\"fv-tip-coner\"></div><div class=\"fv-tip-l\"></div><div id=\"_formValidateMsg\" class=\"fv-tip-r\"></div>");
		}
	}, showFormTip:function (b, c, d) {
		if (b.css("display") != "none") {
			b = b.offset();
			c = a("#_formValidateTip");
			a("#_formValidateMsg").html(d || "");
			d = c.width();
			var e = a(window).width();
			if (b.left + d + 20 > e) {
				b.left = b.left - d + 15;
				c.removeClass("tip-left").addClass("tip-right");
			} else {
				c.removeClass("tip-right").addClass("tip-left");
			}
			c.css({left:b.left, top:b.top + 16}).show();
		}
	}, hideFormTip:function () {
		a("#_formValidateTip").hide();
		a("#_formValidateMsg").html("");
	}, errorsFor:function (b) {
		var c = this.idOrName(b);
		return this.errors().filter(function () {
			return a(this).attr("for") == c;
		});
	}, idOrName:function (b) {
		return this.groups[b.name] || (this.checkable(b) ? b.name : b.id || b.name);
	}, checkable:function (b) {
		return /radio|checkbox/i.test(b.type);
	}, findByName:function (b) {
		var c = this.currentForm;
		return a(document.getElementsByName(b)).map(function (d, e) {
			return e.form == c && e.name == b && e || null;
		});
	}, getLength:function (b, c) {
		switch (c.nodeName.toLowerCase()) {
		  case "select":
			return a("option:selected", c).length;
		  case "input":
			if (this.checkable(c)) {
				return this.findByName(c.name).filter(":checked").length;
			}
		}
		return b.length;
	}, depend:function (b, c) {
		return this.dependTypes[typeof b] ? this.dependTypes[typeof b](b, c) : true;
	}, dependTypes:{"boolean":function (b) {
		return b;
	}, string:function (b, c) {
		return !!a(b, c.form).length;
	}, "function":function (b, c) {
		return b(c);
	}}, optional:function (b) {
		return !a.validator.methods.required.call(this, a.trim(b.value), b) && "dependency-mismatch";
	}, startRequest:function (b) {
		if (!this.pending[b.name]) {
			this.pendingRequest++;
			this.pending[b.name] = true;
		}
	}, stopRequest:function (b, c) {
		this.pendingRequest--;
		if (this.pendingRequest < 0) {
			this.pendingRequest = 0;
		}
		delete this.pending[b.name];
		if (c && this.pendingRequest == 0 && this.formSubmitted && this.form()) {
			a(this.currentForm).submit();
			this.formSubmitted = false;
		} else {
			if (!c && this.pendingRequest == 0 && this.formSubmitted) {
				a(this.currentForm).triggerHandler("invalid-form", [this]);
				this.formSubmitted = false;
			}
		}
	}, previousValue:function (b) {
		return a.data(b, "previousValue") || a.data(b, "previousValue", {old:null, valid:true, message:this.defaultMessage(b, "remote")});
	}}, classRuleSettings:{required:{required:true}, email:{email:true}, url:{url:true}, date:{date:true}, dateISO:{dateISO:true}, dateDE:{dateDE:true}, number:{number:true}, numberDE:{numberDE:true}, digits:{digits:true}, creditcard:{creditcard:true}}, addClassRules:function (b, c) {
		b.constructor == String ? (this.classRuleSettings[b] = c) : a.extend(this.classRuleSettings, b);
	}, classRules:function (b) {
		var c = {};
		(b = a(b).attr("class")) && a.each(b.split(" "), function () {
			this in a.validator.classRuleSettings && a.extend(c, a.validator.classRuleSettings[this]);
		});
		return c;
	}, attributeRules:function (b) {
		var c = {};
		b = a(b);
		for (method in a.validator.methods) {
			var d = b.attr(method);
			if (d) {
				c[method] = d;
			}
		}
		c.maxlength && /-1|2147483647|524288/.test(c.maxlength) && delete c.maxlength;
		return c;
	}, metadataRules:function (b) {
		if (!a.metadata) {
			return {};
		}
		var c = a.data(b.form, "validator").settings.meta;
		return c ? a(b).metadata()[c] : a(b).metadata();
	}, staticRules:function (b) {
		var c = {}, d = a.data(b.form, "validator");
		if (d.settings.rules) {
			c = a.validator.normalizeRule(d.settings.rules[b.name]) || {};
		}
		return c;
	}, normalizeRules:function (b, c) {
		a.each(b, function (d, e) {
			if (e === false) {
				delete b[d];
			} else {
				if (e.param || e.depends) {
					var f = true;
					switch (typeof e.depends) {
					  case "string":
						f = !!a(e.depends, c.form).length;
						break;
					  case "function":
						f = e.depends.call(c, c);
						break;
					}
					if (f) {
						b[d] = e.param !== undefined ? e.param : true;
					} else {
						delete b[d];
					}
				}
			}
		});
		a.each(b, function (d, e) {
			b[d] = a.isFunction(e) ? e(c) : e;
		});
		a.each(["minlength", "maxlength", "min", "max"], function () {
			if (b[this]) {
				b[this] = Number(b[this]);
			}
		});
		a.each(["rangelength", "range"], function () {
			if (b[this]) {
				b[this] = [Number(b[this][0]), Number(b[this][1])];
			}
		});
		if (a.validator.autoCreateRanges) {
			if (b.min && b.max) {
				b.range = [b.min, b.max];
				delete b.min;
				delete b.max;
			}
			if (b.minlength && b.maxlength) {
				b.rangelength = [b.minlength, b.maxlength];
				delete b.minlength;
				delete b.maxlength;
			}
		}
		b.messages && delete b.messages;
		return b;
	}, normalizeRule:function (b) {
		if (typeof b == "string") {
			var c = {};
			a.each(b.split(/\s/), function () {
				c[this] = true;
			});
			b = c;
		}
		return b;
	}, addMethod:function (b, c, d) {
		a.validator.methods[b] = c;
		a.validator.messages[b] = d != undefined ? d : a.validator.messages[b];
		c.length < 3 && a.validator.addClassRules(b, a.validator.normalizeRule(b));
	}, methods:{required:function (b, c, d) {
		if (!this.depend(d, c)) {
			return "dependency-mismatch";
		}
		switch (c.nodeName.toLowerCase()) {
		  case "select":
			return (b = a(c).val()) && b.length > 0;
		  case "input":
			if (this.checkable(c)) {
				return this.getLength(b, c) > 0;
			}
		  default:
			return a.trim(b).length > 0;
		}
	}, remote:function (b, c, d) {
		if (this.optional(c)) {
			return "dependency-mismatch";
		}
		var e = this.previousValue(c);
		this.settings.messages[c.name] || (this.settings.messages[c.name] = {});
		e.originalMessage = this.settings.messages[c.name].remote;
		this.settings.messages[c.name].remote = e.message;
		d = typeof d == "string" && {url:d} || d;
		if (e.old !== b) {
			e.old = b;
			var f = this;
			this.startRequest(c);
			var j = {};
			j[c.name] = b;
			a.ajax(a.extend(true, {url:d, mode:"abort", port:"validate" + c.name, dataType:"json", data:j, success:function (n) {
				f.settings.messages[c.name].remote = e.originalMessage;
				var r = n === true;
				if (r) {
					var p = f.formSubmitted;
					f.prepareElement(c);
					f.formSubmitted = p;
					f.successList.push(c);
					f.showErrors();
				} else {
					p = {};
					n = e.message = n || f.defaultMessage(c, "remote");
					p[c.name] = a.isFunction(n) ? n(b) : n;
					f.showErrors(p);
				}
				e.valid = r;
				f.stopRequest(c, r);
			}}, d));
			return "pending";
		} else {
			if (this.pending[c.name]) {
				return "pending";
			}
		}
		return e.valid;
	}, minlength:function (b, c, d) {
		return this.optional(c) || this.getLength(a.trim(b), c) >= d;
	}, maxlength:function (b, c, d) {
		return this.optional(c) || this.getLength(a.trim(b), c) <= d;
	}, rangelength:function (b, c, d) {
		b = this.getLength(a.trim(b), c);
		return this.optional(c) || b >= d[0] && b <= d[1];
	}, min:function (b, c, d) {
		return this.optional(c) || b >= d;
	}, max:function (b, c, d) {
		return this.optional(c) || b <= d;
	}, range:function (b, c, d) {
		return this.optional(c) || b >= d[0] && b <= d[1];
	}, email:function (b, c) {
		return this.optional(c) || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(b);
	}, url:function (b, c) {
		return this.optional(c) || /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(b);
	}, date:function (b, c) {
		return this.optional(c) || !/Invalid|NaN/.test(new Date(b));
	}, dateISO:function (b, c) {
		return this.optional(c) || /^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/.test(b);
	}, number:function (b, c) {
		return this.optional(c) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(b);
	}, digits:function (b, c) {
		return this.optional(c) || /^\d+$/.test(b);
	}, creditcard:function (b, c) {
		if (this.optional(c)) {
			return "dependency-mismatch";
		}
		if (/[^0-9-]+/.test(b)) {
			return false;
		}
		var d = 0, e = 0, f = false;
		b = b.replace(/\D/g, "");
		for (var j = b.length - 1; j >= 0; j--) {
			e = b.charAt(j);
			e = parseInt(e, 10);
			if (f) {
				if ((e *= 2) > 9) {
					e -= 9;
				}
			}
			d += e;
			f = !f;
		}
		return d % 10 == 0;
	}, accept:function (b, c, d) {
		d = typeof d == "string" ? new RegExp(d) : d;
		return this.optional(c) || d.test(b);
	}, equalTo:function (b, c, d) {
		d = a(d).unbind(".validate-equalTo").bind("blur.validate-equalTo", function () {
			a(c).valid();
		});
		return b == d.val();
	}}});
	a.format = a.validator.format;
})(jQuery);
(function (a) {
	var b = a.ajax, c = {};
	a.ajax = function (d) {
		d = a.extend(d, a.extend({}, a.ajaxSettings, d));
		var e = d.port;
		if (d.mode == "abort") {
			c[e] && c[e].abort();
			return c[e] = b.apply(this, arguments);
		}
		return b.apply(this, arguments);
	};
})(jQuery);
(function (a) {
	a.each({focus:"focusin", blur:"focusout"}, function (b, c) {
		a.event.special[c] = {setup:function () {
			if (a.browser.msie) {
				return false;
			}
			this.addEventListener(b, a.event.special[c].handler, true);
		}, teardown:function () {
			if (a.browser.msie) {
				return false;
			}
			this.removeEventListener(b, a.event.special[c].handler, true);
		}, handler:function (d) {
			arguments[0] = a.event.fix(d);
			arguments[0].type = c;
			return a.event.handle.apply(this, arguments);
		}};
	});
	a.extend(a.fn, {delegate:function (b, c, d) {
		return this.bind(b, function (e) {
			var f = a(e.target);
			if (f.is(c)) {
				return d.apply(f, arguments);
			}
		});
	}, triggerEvent:function (b, c) {
		return this.triggerHandler(b, [a.event.fix({type:b, target:c})]);
	}});
})(jQuery);
$.metadata.setType("attr", "validate");
$.validator.messages = {required:"\u8bf7\u8f93\u5165\u503c\uff01", remote:"\u8bf7\u68c0\u67e5\u8be5\u503c\u7684\u6b63\u786e\u6027\uff01", email:"\u8bf7\u8f93\u5165\u6b63\u786e\u683c\u5f0f\u7684Email\u5730\u5740\uff01", url:"\u8bf7\u8f93\u5165\u6b63\u786e\u683c\u5f0f\u7684URL\u5730\u5740\uff01", date:"\u8bf7\u8f93\u5165\u6709\u6548\u7684\u65e5\u671f\u683c\u5f0f", dateISO:"\u8bf7\u8f93\u5165\u6b63\u786eISO\u683c\u5f0f\u7684\u65e5\u671f\uff01.", number:"\u8bf7\u8f93\u5165\u6570\u5b57\uff01", digits:"\u8bf7\u8f93\u5165\u6574\u578b\u6570\u5b57\uff01", creditcard:"\u8bf7\u8f93\u5165\u6709\u6548\u7684\u4fe1\u7528\u5361\u53f7\u7801\uff01", equalTo:"\u4e24\u6b21\u8f93\u5165\u7684\u503c\u4e0d\u4e00\u81f4\uff01", accept:"\u8f93\u5165\u7684\u4fe1\u606f\u4e0d\u5339\u914d\u5b9a\u4e49\u7684\u89c4\u5219\uff01", maxlength:$.validator.format("\u8f93\u5165\u7684\u5b57\u7b26\u957f\u5ea6\u8d85\u51fa\u4e86 {0} \u4f4d\uff01"), minlength:$.validator.format("\u8bf7\u8f93\u5165\u81f3\u5c11 {0} \u4f4d\u957f\u5ea6\u7684\u5b57\u7b26\uff01"), rangelength:$.validator.format("\u8bf7\u8f93\u5165 {0} - {1} \u4f4d\u957f\u5ea6\u7684\u5b57\u7b26\uff01"), range:$.validator.format("\u8bf7\u8f93\u5165 {0} - {1} \u8303\u56f4\u4e4b\u95f4\u7684\u503c\uff01"), max:$.validator.format("\u8bf7\u8f93\u5165\u4e0d\u5927\u4e8e {0} \u7684\u503c\uff01"), min:$.validator.format("\u8bf7\u8f93\u5165\u4e0d\u5c0f\u4e8e {0} \u7684\u503c\uff01")};
jQuery.validator.addMethod("regExp", function (a, b, c) {
	c = typeof c == "string" ? new RegExp(c) : c;
	return this.optional(b) || c.test(a);
}, "\u8f93\u5165\u7684\u4fe1\u606f\u4e0d\u5339\u914d\u5b9a\u4e49\u7684\u89c4\u5219\uff01");
jQuery.validator.addMethod("CNRangeLength", function (a, b, c) {
	a = a.replace(/[^\x00-\xff]/g, "***").length;
	c[3] = parseInt(c[1] / 3, 10);
	return this.optional(b) || a >= c[0] && a < c[1];
}, $.format("\u8bf7\u8f93\u5165{0}-{1}\u4e2a\u5b57\u7b26\u6216{0}-{3}\u4e2a\u6c49\u5b57\uff01"));
jQuery.validator.addMethod("postalcode", function (a, b) {
	return this.optional(b) || /^[1-9]\d{5}(?!\d)$/.test(a);
}, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u90ae\u653f\u7f16\u7801\uff01");
jQuery.validator.addMethod("IP", function (a, b) {
	return this.optional(b) || /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/.test($.trim(a));
}, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684IP\u5730\u5740\uff01");
jQuery.validator.addMethod("MAC", function (a, b) {
	return this.optional(b) || /^([0-9a-fA-F]){2}(([\s-][0-9a-fA-F]{2}){5})$/.test($.trim(a));
}, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684MAC\u5730\u5740(\u4ee5 - \u5206\u9694)\uff01");
jQuery.validator.addMethod("telephone", function (a, b) {
	return this.optional(b) || /^(\d{3,4}-)\d{7,8}$/.test($.trim(a));
}, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u56fa\u5b9a\u7535\u8bdd\u53f7\u7801\uff01\u683c\u5f0f\uff1a\u533a\u53f7-\u53f7\u7801");
jQuery.validator.addMethod("mobile", function (a, b) {
	return this.optional(b) || /^(13[0-9]|15[0-9]|18[0-9])\d{8}$/.test($.trim(a));
}, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u624b\u673a\u53f7\u7801\uff01");
jQuery.validator.addMethod("dateCN", function (a, b) {
	var c = /^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
	return this.optional(b) || c.test($.trim(a));
}, "\u8bf7\u8f93\u5165\u6b63\u786e\u683c\u5f0f\u7684\u65e5\u671f(yyyy-MM-dd)\uff01");
jQuery.validator.addMethod("chinese", function (a, b) {
	a = a.replace(/(^\s*)|(\s*$)/g, "");
	return this.optional(b) || /^[\u4e00-\u9fa5]+$/gi.test($.trim(a));
}, "\u8bf7\u8f93\u5165\u4e2d\u6587\u5b57\u7b26\uff01");
jQuery.validator.addMethod("validChar", function (a, b) {
	return this.optional(b) || !/[`~\^\'\"]+/g.test($.trim(a));
}, "\u8f93\u5165\u4fe1\u606f\u4e2d\u542b\u6709\u975e\u6cd5\u5b57\u7b26 ` ' \" ~ ^ ");
jQuery.validator.addMethod("IDCard", function (a, b) {
	return this.optional(b) || _checkIDCard_($.trim(a));
}, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u8eab\u4efd\u8bc1\u53f7\u7801\uff01");
function _checkIDCard_(a) {
	var b = false, c = {11:"\u5317\u4eac", 12:"\u5929\u6d25", 13:"\u6cb3\u5317", 14:"\u5c71\u897f", 15:"\u5185\u8499\u53e4", 21:"\u8fbd\u5b81", 22:"\u5409\u6797", 23:"\u9ed1\u9f99\u6c5f", 31:"\u4e0a\u6d77", 32:"\u6c5f\u82cf", 33:"\u6d59\u6c5f", 34:"\u5b89\u5fbd", 35:"\u798f\u5efa", 36:"\u6c5f\u897f", 37:"\u5c71\u4e1c", 41:"\u6cb3\u5357", 42:"\u6e56\u5317", 43:"\u6e56\u5357", 44:"\u5e7f\u4e1c", 45:"\u5e7f\u897f", 46:"\u6d77\u5357", 50:"\u91cd\u5e86", 51:"\u56db\u5ddd", 52:"\u8d35\u5dde", 53:"\u4e91\u5357", 54:"\u897f\u85cf", 61:"\u9655\u897f", 62:"\u7518\u8083", 63:"\u9752\u6d77", 64:"\u5b81\u590f", 65:"\u65b0\u7586", 71:"\u53f0\u6e7e", 81:"\u9999\u6e2f", 82:"\u6fb3\u95e8", 91:"\u56fd\u5916"}, d = function (e) {
		return /^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/.test(e) ? true : false;
	};
	if (/(^\d{15}$)|(^\d{17}([0-9]|X|x)$)/.test(a)) {
		if (c[a.substr(0, 2)] == null || c[a.substring(0, 2)] == "") {
			return b = false;
		} else {
			switch (a.length) {
			  case 15:
				if (d(parseInt(a.substr(6, 2), 10) + 1900 + "-" + a.substr(8, 2) + "-" + a.substr(10, 2))) {
					b = true;
				} else {
					return b = false;
				}
				break;
			  case 18:
				if (!d(a.substr(6, 4) + "-" + a.substr(10, 2) + "-" + a.substr(12, 2))) {
					return b = false;
				}
				a = a.split("");
				if ("10X98765432".substr(((parseInt(a[0], 10) + parseInt(a[10], 10)) * 7 + (parseInt(a[1], 10) + parseInt(a[11], 10)) * 9 + (parseInt(a[2], 10) + parseInt(a[12], 10)) * 10 + (parseInt(a[3], 10) + parseInt(a[13], 10)) * 5 + (parseInt(a[4], 10) + parseInt(a[14], 10)) * 8 + (parseInt(a[5], 10) + parseInt(a[15], 10)) * 4 + (parseInt(a[6], 10) + parseInt(a[16], 10)) * 2 + parseInt(a[7], 10) * 1 + parseInt(a[8], 10) * 6 + parseInt(a[9], 10) * 3) % 11, 1) != a[17]) {
					return b = false;
				}
				b = true;
				break;
			}
		}
	} else {
		return b = false;
	}
	return b;
}
(function (a) {
	var b = {gDiv_prefix:"", hDiv_prefix:"gridHeader-", bDiv_prefix:"gridBody-", bDivBody_prefix:"gridBodyBox-", togColBtn_prefix:"togColBtn-", togColList_prefix:"togColList-", colDrag_prefix:"colDrag-", colDragLine_prefix:"colDragLine-", dragLayer_prefix:"dragLayer-"};
	a.renderGrid = function (c, d) {
		if (c.grid) {
			return false;
		}
		d = a.extend({width:false, height:false, formName:null, formAction:null, onChangeSort:false, onToggleCol:false, onSwitchCol:false, onResizeCol:false, onRowClick:false, onToggleGroup:false}, d);
		var e = {id:null, gDiv:null, hDiv:null, bDiv:null, bDivBody:null, togColBtn:null, togColList:null, colDrag:null, colDragLine:null, dragLayer:null, minWidth:30, minTogCol:1, colOffset:{}, fixWidth:function (g) {
			if (g == null || "undefined" == g || g < 0 || "auto" == g || isNaN(parseInt(g, 10))) {
				return false;
			}
			a(this.togColList).hide();
			a(this.togColBtn).hide();
			g = parseInt(g, 10);
			a(this.gDiv).width(g);
			a(this.hDiv).width(g - 1);
			a(this.bDiv).width(g - 1);
		}, fixHeight:function (g) {
			if (g == null || "undefined" == g || g < 0 || "auto" == g || isNaN(parseInt(g, 10))) {
				return false;
			}
			a(this.togColList).hide();
			a(this.togColBtn).hide();
			g = parseInt(g, 10);
			a(this.gDiv).height(g);
			a(this.bDiv).height(a(this.gDiv).height() - a(this.hDiv).height() - 2);
			hDivH = null;
		}, dragStart:function (g, l, h) {
			a(this.togColList).hide();
			a(this.togColBtn).hide();
			if ("colresize" == g) {
				document.all && this.colDragLine.setCapture();
				g = parseInt(a(this.resizeCol).position().left, 10) + parseInt(a(this.resizeCol).outerWidth(), 10) - 1;
				a(this.colDragLine).css("left", g).height(a(this.gDiv).height()).show();
				l = a(this.resizeCol).position().left;
				this.colresize = {startX:this.posEvt(h).x, ol:l, dl:g};
				a(this.dragLayer).css({"left:":"0px", top:"0px", cursor:"col-resize", display:"block"});
			} else {
				if ("colmove" == g) {
					this.colMoved = false;
					this.mcol = l;
					this.mcoln = a("th", this.hDiv).index(l);
					this.colCopy = document.createElement("div");
					this.colCopy.className = "colCopy";
					a("body").append(this.colCopy);
					this.colCopy.style.display = "none";
					this.colCopy.innerHTML = "<div class='copy-div'>" + l.innerHTML + "</div>";
					this.colMovePos = document.createElement("div");
					this.colMovePos.className = "colMove-pos";
					a("body").append(this.colMovePos);
					this.colMovePos.style.top = a(this.hDiv).offset().top - 8 + "px";
					a(this.dragLayer).css({"left:":"0px", top:a(this.hDiv).height() + 1, cursor:"default", display:"block"});
				}
			}
			a("body").noSelect();
		}, dragMove:function (g, l) {
			if ("colresize" == g) {
				if (!this.colResized) {
					this.colResized = true;
				}
				if (this.posEvt(l).x - this.colresize.ol <= this.minWidth) {
					return false;
				}
				a(this.colDragLine).css("left", this.posEvt(l).x - this.colresize.startX + this.colresize.dl);
			} else {
				if ("colmove" == g) {
					if (!this.colMoved) {
						this.colMoved = true;
						a(this.colCopy).css("display", "block");
					}
					a(this.colCopy).css({top:this.posEvt(l).y + 10, left:this.posEvt(l).x + 20});
					if (this.mcolt == null || this.mcolt == this.mcoln) {
						a(this.colCopy).removeClass("drop-yes").addClass("drop-no");
						a(this.colMovePos).hide();
					} else {
						a(this.colCopy).removeClass("drop-no").addClass("drop-yes");
						var h = a("th:eq(" + this.mcolt + ")", this.hDiv), m = null;
						if (this.mcolt > this.mcoln) {
							m = h.offset().left + h.width() - 4;
						} else {
							if (this.mcolt < this.mcoln) {
								m = h.offset().left - 5;
							}
						}
						a(this.colMovePos).css("left", m).show();
					}
				}
			}
			return false;
		}, dragEnd:function (g, l) {
			document.onmousemove = null;
			document.onmouseup = null;
			if ("colresize" == g) {
				document.all && this.colDragLine.releaseCapture();
				a(this.dragLayer).hide();
				a(this.colDrag).hide();
				a(this.colDragLine).hide();
				if (!this.colResized) {
					return false;
				}
				var h = parseInt(a("div:first", this.resizeCol).css("width"), 10) + this.posEvt(l).x - this.colresize.startX;
				if (h <= this.minWidth) {
					h = this.minWidth;
				}
				a("div:first", this.resizeCol).width(h);
				var m = a("th", this.hDiv).index(this.resizeCol), i = a(this.bDivBody).find("div.grid-row"), k = a("table", this.hDiv).first().width();
				if (i.length > 0) {
					for (var s = 0; s < i.length; s++) {
						i[s].style.width = k + "px";
						i[s].children[0].rows[0].cells[m].children[0].style.width = h + "px";
					}
				}
				i = null;
				if (d.onResizeCol && "function" == typeof d.onResizeCol) {
					var t = [];
					a("th", this.hDiv).each(function () {
						t.push("\"" + a(this).attr("field") + "\":" + parseInt(a("div:first", this).css("width"), 10));
					});
					d.onResizeCol(a(this.resizeCol).attr("field"), h, "{ " + t.join(", ") + " }");
					t = null;
				}
				h = m = null;
				this.autoScroll();
				this.colresize = null;
				this.colResized = false;
				this.resizeCol = null;
			} else {
				if ("colmove" == g) {
					a(this.dragLayer).hide();
					this.colCopy.innerHTML = "";
					a(this.colCopy).remove();
					this.colCopy = null;
					a(this.colMovePos).remove();
					this.colMovePos = null;
					this.switchCol(this.mcoln, this.mcolt);
					this.colMoved = false;
					this.mcolt = this.mcoln = this.colOffset = this.mcol = null;
				}
			}
			a("body").css("cursor", "default");
			a("body").noSelect(false);
		}, changeSort:function (g, l) {
			a(this.togColList).hide();
			a(this.togColBtn).hide();
			if (!g || g == "") {
				alert("\u672a\u53d1\u73b0\u6709\u6548\u7684field\u503c\uff01");
				return false;
			}
			var h = a("#sortname-" + this.id), m = a("#sortorder-" + this.id);
			if (!l || l == null || l == "") {
				l = h.val().toLowerCase() == g.toLowerCase() ? m.val().toLowerCase() == "desc" ? "asc" : "desc" : "desc";
			}
			a("#sortname-" + this.id).val(g);
			a("#sortorder-" + this.id).val(l);
			if (d.onChangeSort && "function" == typeof d.onChangeSort) {
				d.onChangeSort(g, l);
			} else {
				if (!this.formName) {
					this.formName = a("#" + this.id).parent("form").attr("name");
				}
				if (this.formAction) {
					document.forms[this.formName].action = this.formAction;
				}
				document.forms[this.formName].submit();
			}
		}, switchCol:function (g, l) {
			if (l != null) {
				this.togColList.innerHTML = "";
				a(this.togColList).hide();
				if (g === l) {
					return false;
				} else {
					if (g > l) {
						a("th:eq(" + l + ")", this.hDiv).before(this.mcol);
						var h = a(this.bDivBody).find("div.grid-row"), m = null;
						if (h.length > 0) {
							for (var i = 0; i < h.length; i++) {
								m = null;
								m = h[i].children[0].rows[0];
								m.insertBefore(m.cells[g], m.cells[l]);
							}
						}
						m = h = null;
					} else {
						if (g < l) {
							a("th:eq(" + l + ")", this.hDiv).after(this.mcol);
							h = a(this.bDivBody).find("div.grid-row");
							var k = m = null;
							if (h.length > 0) {
								for (i = 0; i < h.length; i++) {
									k = m = null;
									m = h[i].children[0].rows[0];
									(k = m.cells[l].nextSibling) ? m.insertBefore(m.cells[g], k) : m.appendChild(m.cells[g]);
								}
							}
							k = m = h = null;
						}
					}
				}
				if (d.onSwitchCol && "function" == typeof d.onSwitchCol) {
					var s = [];
					a("th", this.hDiv).each(function (t) {
						s.push("\"" + a(this).attr("field") + "\":" + t);
					});
					d.onSwitchCol("{ " + s.join(", ") + " }");
					s = null;
				}
			}
			this.autoScroll();
		}, togCol:function (g, l) {
			var h = a("input[name='togColCb']:checked", this.togColList).length, m = a("#cb-" + g + "-" + this.id)[0];
			if (h <= this.minTogCol && !l) {
				return false;
			}
			if (l) {
				a("th:eq(" + g + ")", this.hDiv).show();
				m.checked = true;
				h = a("table", this.hDiv).first().width();
				m = a(this.bDivBody).find("div.grid-row");
				if (m.length > 0) {
					for (var i = 0; i < m.length; i++) {
						m[i].style.width = h + "px";
						m[i].children[0].rows[0].cells[g].style.display = "";
					}
				}
			} else {
				a("th:eq(" + g + ")", this.hDiv).hide();
				m.checked = false;
				h = a("table", this.hDiv).first().width();
				m = a(this.bDivBody).find("div.grid-row");
				if (m.length > 0) {
					for (i = 0; i < m.length; i++) {
						m[i].children[0].rows[0].cells[g].style.display = "none";
						m[i].style.width = h + "px";
					}
				}
			}
			m = h = m = null;
			this.autoScroll();
			if (d.onToggleCol && "function" == typeof d.onToggleCol) {
				var k = [];
				a("th", this.hDiv).each(function () {
					k.push("\"" + a(this).attr("field") + "\":\"" + (a(this).css("display") == "none" ? "false" : "true") + "\"");
				});
				d.onToggleCol(a("th:eq(" + g + ")", this.hDiv).attr("field"), l, "{ " + k.join(", ") + " }");
				k = null;
			}
		}, showTogColList:function () {
			if (a(this.togColList).css("display") != "none") {
				a(this.togColList).hide();
				return false;
			}
			var g = a("th", this.hDiv).length + 3, l = parseInt((a(this.bDiv).height() - 18) / 25, 10);
			if (l > g) {
				l = g;
			}
			var h = a("tr[id]", this.togColList).length;
			if (h > 0 && h == l) {
				a(this.togColList).toggle();
				h = null;
				return false;
			}
			g = Math.ceil((g - l) / l);
			var m = this.id, i = null, k = 0, s = null, t = [];
			h = "<div id=\"dodesc-" + m + "\" class=\"tog-col-row\" dosort=\"desc\"><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td class=\"ndcol1\"><span class=\"do-desc-ico\">&nbsp;</span></td><td class=\"ndcol2\">\u964d\u5e8f</td></tr></table></div>";
			t.push("<div id=\"doasc-" + m + "\" class=\"tog-col-row\" dosort=\"asc\"><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td class=\"ndcol1\"><span class=\"do-asc-ico\">&nbsp;</span></td><td class=\"ndcol2\">\u5347\u5e8f</td></tr></table></div>");
			t.push(h);
			t.push("<span class=\"tog-split\">&nbsp;</span>");
			a("th", this.hDiv).each(function () {
				i = this.style.display != "none" ? "checked" : "";
				s = a("div:first", this).html();
				t.push("<div class=\"tog-col-row\" col=\"" + k + "\"><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td class=\"ndcol1\"><input type=\"checkbox\" id=\"cb-" + k + "-" + m + "\" name=\"togColCb\" value=\"" + k + "\" " + i + "/></td><td class=\"ndcol2\">" + s + "</td></tr></table></div>");
				k++;
			});
			h = h = h = h = h = s = k = i = m = null;
			var B = [];
			B.push("<table class=\"togcol-table" + (g > 0 ? " cascade" : "") + "\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody>");
			for (var w = 0; w < l; w++) {
				B.push("<tr>");
				for (var A = 0; A <= g; A++) {
					h = t[A * l + w];
					if (!h || h == null) {
						h = "&nbsp;";
					}
					B.push("<td" + (A == 0 ? " class=\"tog-col\"" : " class=\"col-cascade\"") + ">" + h + "</td>");
				}
				B.push("</tr>");
			}
			B.push("</tbody></table>");
			this.togColList.innerHTML = B.join("\n");
			h = h = B = null;
			a("div.tog-col-row", this.togColList).each(function () {
				a(this).click(function () {
					if (this.disabled || a(this).attr("disabled") == "true") {
						return false;
					}
					var v = a(this).attr("col"), D = a(this).attr("dosort");
					if (v && v != "") {
						D = a("#cb-" + v + "-" + e.id)[0].checked;
						e.togCol(v, !D);
					} else {
						if (D && D != "") {
							v = a(e.colDrag.targetEle);
							v.attr("sortable") && v.attr("sortable") == "true" && e.changeSort(v.attr("field"), D);
						}
					}
				}).hover(function () {
					if (this.disabled || a(this).attr("disabled") == "true") {
						return false;
					}
					a(this).addClass("hover");
				}, function () {
					if (this.disabled || a(this).attr("disabled") == "true") {
						return false;
					}
					a(this).removeClass("hover");
				});
			});
			a(this.togColList).show();
		}, autoScroll:function () {
			this.hDiv.scrollLeft = this.bDiv.scrollLeft;
		}, addData:function (g) {
			if (!g || g.length <= 0) {
				return false;
			}
			var l = {}, h = null, m = null, i = 0;
			a("th", this.hDiv).each(function () {
				h = a(this);
				m = null;
				m = {};
				m.hide = h.attr("hide");
				m.align = h.attr("align");
				m.width = parseInt(a("div:first", this).css("width"), 10);
				l[h.attr("field")] = m;
				i += m.width;
				document.all || (i += 9);
				m = null;
			});
			var k = m = h = null, s = null, t = null;
			s = null;
			for (var B = this.bDivBody.children.length, w = 0; w < g.length; w++) {
				t = g[w];
				s = k = null;
				k = [];
				k.push("<table class=\"grid-row-table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr>");
				for (var A in l) {
					s = "";
					if (l[A].hide == "true") {
						s = " style=\"display:none;\"";
					}
					k.push("<td align=\"" + l[A].align + "\" hide=\"" + l[A].hide + "\"" + s + "><div class=\"grid-row-cell\" style=\"width:" + l[A].width + "px;text-align:" + l[A].align + ";\">" + t[A] + "</div></td>");
				}
				k.push("</tr></table>");
				s = a("<div style=\"width:" + i + "px;\"/>");
				a(this.bDivBody).append(s);
				s.hide().addClass("grid-row " + ((w + 1 + B) % 2 == 0 ? "row-even" : "row-odd")).html(k.join("")).mouseover(function () {
					a(this).addClass("row-hover");
				}).mouseout(function () {
					a(this).removeClass("row-hover");
				}).click(function () {
					a("div.row-sel", this.bDivBody).removeClass("row-sel");
					a(this).addClass("row-sel");
				});
				s.show();
			}
			l = s = t = s = k = null;
		}, clearData:function () {
			this.bDivBody.innerHTML = "";
		}, deleteRow:function (g) {
			if (g && g != "") {
				if ("object" == typeof g) {
					for (var l = 0; l < g.length; l++) {
						a("#grid-row-" + g[l]).html("").remove();
					}
				} else {
					a("#grid-row-" + g).html("").remove();
				}
			}
		}, selectAll:function (g) {
			var l = a("div.grid-row", this.bDivBody);
			if (g === true) {
				for (g = 0; g < l.length; g++) {
					a(l[g]).addClass("row-sel");
				}
			} else {
				if (g === false) {
					for (g = 0; g < l.length; g++) {
						a(l[g]).removeClass("row-sel");
					}
				}
			}
		}, selectRow:function (g, l) {
			if (g && g != "") {
				l === true ? a("#grid-row-" + g).addClass("row-sel") : a("#grid-row-" + g).removeClass("row-sel");
			}
		}, initCallback:function (g) {
			d = !g || g == null ? a.extend(d, {onChangeSort:false, onToggleCol:false, onSwitchCol:false, onResizeCol:false, onRowClick:false}) : a.extend(d, g);
		}, toggleGroups:function (g) {
			var l = a("div.grid-group", this.bDivBody);
			if (g) {
				for (g = 0; g < l.length; g++) {
					a(l[g]).removeClass("group-collapsed");
					d.onToggleGroup && "function" == typeof d.onToggleGroup && d.onToggleGroup(a("div.grid-group-rows", l[g]).get(0), false);
				}
			} else {
				for (g = 0; g < l.length; g++) {
					a(l[g]).addClass("group-collapsed");
					d.onToggleGroup && "function" == typeof d.onToggleGroup && d.onToggleGroup(a("div.grid-group-rows", l[g]).get(0), true);
				}
			}
		}, posEvt:function (g) {
			g = g || window.event;
			return {x:g.pageX || g.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft), y:g.pageY || g.clientY + (document.documentElement.scrollTop || document.body.scrollTop)};
		}, stopEvt:function (g) {
			g = g || window.event || g;
			if (g.stopPropagation) {
				g.stopPropagation();
			} else {
				g.cancelBubble = true;
			}
		}};
		e.id = d.id;
		e.formName = d.formName;
		e.formAction = d.formAction;
		e.gDiv = document.getElementById(b.gDiv_prefix + e.id);
		e.hDiv = document.getElementById(b.hDiv_prefix + e.id);
		e.bDiv = document.getElementById(b.bDiv_prefix + e.id);
		e.bDivBody = document.getElementById(b.bDivBody_prefix + e.id);
		e.togColBtn = document.getElementById(b.togColBtn_prefix + e.id);
		e.togColList = document.getElementById(b.togColList_prefix + e.id);
		e.colDrag = document.getElementById(b.colDrag_prefix + e.id);
		e.colDragLine = document.getElementById(b.colDragLine_prefix + e.id);
		e.dragLayer = document.getElementById(b.dragLayer_prefix + e.id);
		e.dragLayer.style.filter = "alpha(opacity=10)";
		a(c).show();
		d.width && e.fixWidth(d.width);
		d.height && e.fixHeight(d.height);
		var f = a("#sortname-" + e.id).val(), j = a("#sortorder-" + e.id).val();
		a("th", e.hDiv).each(function () {
			var g = a(this).attr("sortable");
			if (g && ("true" == g || g == true)) {
				var l = a(this).attr("field");
				a(this).click(function () {
					e.changeSort(l, null);
				});
				if (l.toLowerCase() == f.toLowerCase()) {
					a(this).addClass("grid-th-active");
					a("div:first", this).addClass("sort-" + j.toLowerCase());
				}
			}
			a(this).mousedown(function (h) {
				if (h.which != 1 && h.button != 0) {
					return false;
				}
				e.dragStart("colmove", this, h);
				document.onmousemove = function (m) {
					e.dragMove("colmove", m);
					return false;
				};
				document.onmouseup = function (m) {
					e.dragEnd("colmove", m);
					return false;
				};
			}).hover(function () {
				if (e.colCopy) {
					e.mcolt = a("th", e.hDiv).index(this);
					return false;
				}
				a(this).addClass("grid-th-hover");
				a(e.togColBtn).hide();
				a(e.togColList).hide();
				var h = parseInt(a(this).position().left, 10) + parseInt(a(this).outerWidth(), 10);
				a(e.togColBtn).css({left:h - a(e.togColBtn).outerWidth(), top:e.hDiv.offsetTop}).show();
				a(e.colDrag).css("left", h - a(e.colDrag).outerWidth() / 2).show();
				e.colDrag.targetEle = this;
			}, function () {
				a(this).removeClass("grid-th-hover");
				e.mcolt = null;
			});
		});
		a(e.bDiv).scroll(function () {
			e.autoScroll();
		});
		a(e.togColBtn).bind("mousedown", function (g) {
			e.showTogColList();
			var l = parseInt(a(this).css("left"), 10), h = parseInt(a(this).outerWidth(), 10), m = parseInt(a(e.togColList).outerWidth(), 10), i = parseInt(a(e.gDiv).outerWidth(), 10);
			if (l + m > i) {
				l = l - m + h;
			}
			if (a(e.colDrag.targetEle).attr("sortable") == "false") {
				a("#doasc-" + e.id).addClass("tog-col-dis").attr("disabled", "true");
				a("#dodesc-" + e.id).addClass("tog-col-dis").attr("disabled", "true");
			} else {
				a("#doasc-" + e.id).removeClass("tog-col-dis").removeAttr("disabled");
				a("#dodesc-" + e.id).removeClass("tog-col-dis").removeAttr("disabled");
			}
			a(e.togColList).css({top:a(this).position().top + a(e.hDiv).height(), left:l});
			e.stopEvt(g);
		});
		a(e.togColList).click(function (g) {
			e.stopEvt(g);
		}).mousedown(function (g) {
			e.stopEvt(g);
		}).noSelect();
		a(e.colDrag).mousedown(function (g) {
			if (g.which != 1 && g.button != 0) {
				return false;
			}
			e.resizeCol = this.targetEle;
			e.dragStart("colresize", this, g);
			document.onmousemove = function (l) {
				e.dragMove("colresize", l);
				return false;
			};
			document.onmouseup = function (l) {
				e.dragEnd("colresize", l);
				return false;
			};
			e.stopEvt(g);
			return false;
		});
		var n = a(e.bDivBody).find("div.grid-row"), r = null, p = a("table", e.hDiv).first().width();
		if (n.length && n.length > 0) {
			for (var x = 0; x < n.length; x++) {
				r = null;
				r = n[x];
				r.style.width = p + "px";
				(x + 1) % 2 == 0 ? a(r).addClass("row-even") : a(r).addClass("row-odd");
				r.onmouseover = function () {
					a(this).addClass("row-hover");
				};
				r.onmouseout = function () {
					a(this).removeClass("row-hover");
				};
				r.onclick = function () {
					a("div.row-sel", this.bDivBody).removeClass("row-sel");
					a(this).addClass("row-sel");
					if (d.onRowClick && "function" == typeof d.onRowClick) {
						d.onRowClick(this);
					} else {
						a(this).attr("click") != undefined && eval(a(this).attr("click"));
					}
				};
			}
		}
		r = n = null;
		a("div.grid-group-hd", this.bDivBody).click(function () {
			a(this).parent("div.grid-group").toggleClass("group-collapsed");
			if (d.onToggleGroup && "function" == typeof d.onToggleGroup) {
				var g = a(this).siblings("div.grid-group-rows").first(), l = g.css("display") == "none" ? true : false;
				d.onToggleGroup(g.get(0), l);
			}
		});
		document.onmousedown = function () {
			if (e.togColBtn.style.display != "none") {
				a(e.togColBtn).hide();
				a(e.togColList).hide();
			}
		};
		c.grid = e;
		document.getElementById(e.id).grid = e;
		return c;
	};
	a.fn.DataGrid = function (c) {
		if (!c.id) {
			c.id = a(this).attr("id");
		}
		a.renderGrid(this, c);
	};
	a.fn.setWidth = function (c) {
		if (c == null || "undefined" == c || c < 0 || isNaN(parseInt(c, 10))) {
			return false;
		}
		return this.each(function () {
			this.grid && this.grid.fixWidth(c);
		});
	};
	a.fn.setHeight = function (c) {
		if (c == null || "undefined" == c || c < 0 || isNaN(parseInt(c, 10))) {
			return false;
		}
		return this.each(function () {
			this.grid && this.grid.fixHeight(c);
		});
	};
	a.fn.fillHeight = function (c) {
		if (c == null || "undefined" == c || c < 0 || isNaN(parseInt(c, 10))) {
			return false;
		}
		var d = this, e = null;
		a(document).ready(function () {
			a(document.body).css("overflow", "hidden");
			d.setHeight(a(window).height() - c);
			a(window).resize(function () {
				if (e != null) {
					window.clearTimeout(e);
					e = null;
				}
				e = window.setTimeout(function () {
					d.setHeight(a(window).height() - c);
					window.clearTimeout(e);
					e = null;
				}, 100);
			});
		});
	};
	a.fn.addData = function (c) {
		return this.each(function () {
			this.grid && this.grid.addData(c);
		});
	};
	a.fn.clearData = function () {
		return this.each(function () {
			this.grid && this.grid.clearData();
		});
	};
	a.fn.deleteRow = function (c) {
		return this.each(function () {
			this.grid && this.grid.deleteRow(c);
		});
	};
	a.fn.selectAll = function (c) {
		return this.each(function () {
			this.grid && this.grid.selectAll(c);
		});
	};
	a.fn.selectRow = function (c, d) {
		return this.each(function () {
			this.grid && this.grid.selectRow(c, d);
		});
	};
	a.fn.toggleCol = function (c, d) {
		return this.each(function () {
			this.grid && this.grid.togCol(c, d);
		});
	};
	a.fn.doCallback = function (c) {
		return this.each(function () {
			this.grid && this.grid.initCallback(c);
		});
	};
	a.fn.toggleGroups = function (c) {
		return this.each(function () {
			this.grid && this.grid.toggleGroups(c);
		});
	};
	a.fn.noSelect = function (c) {
		return (prevent = c == null ? true : c) ? this.each(function () {
			if (a.browser.msie || a.browser.safari) {
				a(this).bind("selectstart", function () {
					return false;
				});
			} else {
				if (a.browser.mozilla) {
					a(this).css("MozUserSelect", "none");
					a("body").trigger("focus");
				} else {
					a.browser.opera ? a(this).bind("mousedown", function () {
						return false;
					}) : a(this).attr("unselectable", "on");
				}
			}
		}) : this.each(function () {
			if (a.browser.msie || a.browser.safari) {
				a(this).unbind("selectstart");
			} else {
				if (a.browser.mozilla) {
					a(this).css("MozUserSelect", "inherit");
				} else {
					a.browser.opera ? a(this).unbind("mousedown") : a(this).removeAttr("unselectable", "on");
				}
			}
		});
	};
})(jQuery);
Pagination = {version:"1.0.3", formName:null, formAction:null, pageNo:1, pageSize:20, totalPages:0, totalRows:0, pageNoField:"pageno", pageSizeField:"pagesize", sortname:"", sortorder:"", pagerGo:false, pagerBack:false, init:function (a, b, c, d, e, f, j) {
	this.formName = a;
	this.formAction = b;
	this.pageNo = c;
	this.pageSize = d;
	this.totalRows = e;
	this.pageNoField = f;
	this.pageSizeField = j;
	this.pagerGo = c < this.getTotalPages();
	this.pagerBack = c > 1;
}, getTotalPages:function () {
	return this.totalPages = Math.ceil(this.totalRows / this.pageSize);
}, submitForm:function () {
	if (!(this.formName === null || $.trim(this.formName) == "")) {
		var a = document.forms[this.formName];
		if (this.formAction !== null && $.trim(this.formAction) !== "") {
			a.action = this.formAction;
		}
		a.submit();
	}
}, goPage:function (a) {
	if (!a || a === "" || a === 0) {
		a = 1;
	}
	if (a >= this.getTotalPages()) {
		a = this.getTotalPages();
	}
	$("input[name='" + this.pageNoField + "']").val(a);
	$("input[name='" + this.pageSizeField + "']").val(this.pageSize);
	this.submitForm();
}, refreshPage:function () {
	this.submitForm();
}, resizePage:function (a) {
	this.pageSize = a.value;
	this.goPage(1);
}, updatePageInfo:function (a, b) {
	var c = this.totalPages;
	if (a == this.totalRows && (!b || b == this.pageSize)) {
		return false;
	}
	if (a) {
		this.totalRows = a;
	}
	if (b) {
		this.pageSize = b;
	}
	$("span[name='pagerTotals']").html(this.totalRows);
	$("span[name='pagerPages']").html(this.getTotalPages());
	if (!this.pagerGo && c < this.getTotalPages()) {
		this.pagerGo = true;
		$("td[name='pagerNext']").html("<a href=\"javascript:void(0);\" onclick=\"Pagination.goPage(" + (this.pageNo + 1) + ");return false;\" class=\"pager-next\"></a>");
		$("td[name='pagerLast']").html("<a href=\"javascript:void(0);\" class=\"pager-last\"></a>");
	}
	var d = this;
	$("td[name='pagerLast'] a").removeAttr("onclick").bind("click", function () {
		Pagination.goPage(d.getTotalPages());
		return false;
	});
}};
ToolBar = {version:"1.0.0", click:function (a) {
	$("#" + a).click();
}, enable:function (a) {
	$("#" + a).removeAttr("disabled").removeClass("tbi-disabled");
}, disable:function (a) {
	$("#" + a).attr("disabled", true).addClass("tbi-disabled");
}, absPos:function (a) {
	var b = 0, c = 0;
	do {
		b += a.offsetLeft;
		c += a.offsetTop;
	} while (a = a.offsetParent);
	return {x:b, y:c};
}, showDropMenu:function (a) {
	var b = "tbi-dm-" + $(a).attr("id");
	if (document.getElementById(b)) {
		var c = $(a).attr("dropmenu");
		if (c && $.trim(c) !== "") {
			$("#" + c).hide();
			$.trim($("#" + b).html()) === "" && $("#" + b).css("backgroundImage", "none").html($("#" + c).html());
		}
		c = this.absPos(a).x + 1;
		var d = this.absPos(a).y + $(a).height() + 2, e = $("#" + b).width();
		if (c + e >= document.body.clientWidth) {
			c = document.body.clientWidth - e;
		}
		$(a).addClass("tbi-active");
		$("#" + b).css({position:"absolute", left:c, top:d}).toggle();
	}
}, closeAllDropMenu:function () {
	$("div.gdk-toolbar-item").removeClass("tbi-active");
	$("div.gdk-tbi-dm").hide();
}, isDisabled:function (a) {
	return $(a).attr("disabled") === true || $(a).attr("disabled") == "true";
}, init:function () {
	$("div.gdk-toolbar-item").bind("click", function () {
		var a = $(this);
		if (!ToolBar.isDisabled(this)) {
			a = a.attr("click");
			eval(a);
			ToolBar.showDropMenu(this);
		}
	}).hover(function () {
		ToolBar.isDisabled(this) || $(this).addClass("tbi-hover");
	}, function () {
		ToolBar.isDisabled(this) || $(this).removeClass("tbi-hover");
	});
	$("div.gdk-tbi-dm").bind("click", function (a) {
		if (a.stopPropagation) {
			a.stopPropagation();
		} else {
			a.cancelBubble = true;
		}
	}).bind("mousedown", function (a) {
		if (a.stopPropagation) {
			a.stopPropagation();
		} else {
			a.cancelBubble = true;
		}
	});
	$("tr.tbi-dmi").bind("click", function () {
		var a = $(this);
		if (a.attr("disabled") != "true") {
			a = a.attr("click");
			eval(a);
			ToolBar.closeAllDropMenu();
		}
	});
	$(document).bind("mousedown", function () {
		ToolBar.closeAllDropMenu();
	});
}};
$(function () {
	ToolBar.init();
});
TabNavigator = {version:"1.0.1", open:function (a) {
	$("#" + a).mousedown();
}, enable:function (a) {
	$("#" + a).removeAttr("disabled").removeClass("tab-disabled");
}, disable:function (a) {
	$("#" + a).attr("disabled", "true").addClass("tab-disabled");
}, addHTab:function (a, b) {
	var c = [];
	c.push("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"HTab-item-table\"><tbody><tr><td class=\"HTab-l\"><i>&nbsp;</i></td>");
	b.ico && c.push("<td class=\"HTab-c\"><div class=\"HTab-ico\" style=\"background:url(" + b.ico + ") no-repeat center center;\">&nbsp;</div></td>");
	b.label && c.push("<td class=\"HTab-c\"><div class=\"HTab-text\">" + b.label + "</div></td>");
	c.push("<td class=\"HTab-r\"><i>&nbsp;</i></td></tr></tbody></table>");
	var d = $("#" + a).find("td.HTabNav-td").last();
	d.after("<td class=\"HTabNav-td\"></td>");
	d = $("#" + a).find("td.HTabNav-td").last();
	var e = $("<div class=\"GF-HTab-item\"/>");
	d.append(e);
	e.hide();
	b.id && e.attr("id", b.id);
	if (b.disabled === true || b.disabled == "true") {
		e.attr("disabled", b.disabled).addClass("tab-disabled");
	}
	e.html(c.join(" "));
	c = null;
	e.bind("mousedown", function () {
		if ($(this).attr("disabled") === true || $(this).attr("disabled") == "true") {
			return false;
		}
		$(this).parent("td").siblings().find("div:first").removeClass("HTab-active");
		$(this).addClass("HTab-active");
		if (b.click) {
			"function" === typeof b.click ? b.click() : eval(b.click);
		}
	}).hover(function () {
		$(this).addClass("HTab-hover");
	}, function () {
		$(this).removeClass("HTab-hover");
	});
	e.show();
	b.isopen == "false" || b.isopen == false || e.mousedown();
	d = e = null;
}, addVTab:function (a, b) {
	var c = [];
	c.push("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"VTab-item-table\"><tbody><tr><td class=\"VTab-t\"><i>&nbsp;</i></td></tr>");
	b.ico && c.push("<tr><td align=\"center\" class=\"VTab-c\"><div class=\"VTab-ico\" style=\"background:url(" + b.ico + ") no-repeat center center;\"></div></td></tr>");
	b.label && c.push("<tr><td align=\"center\" class=\"VTab-c\"><div class=\"VTab-text\">" + b.label + "</div></td></tr>");
	c.push("<tr><td class=\"VTab-b\"><i>&nbsp;</i></td></tr></tbody></table>");
	var d = $("#" + a).find("div.GF-VTab-item").last();
	d.after("<div class=\"GF-VTab-item\"></div>");
	d = null;
	d = $("#" + a).find("div.GF-VTab-item").last();
	d.hide();
	b.id && d.attr("id", b.id);
	if (b.disabled === true || b.disabled == "true") {
		d.attr("disabled", b.disabled).addClass("tab-disabled");
	}
	d.html(c.join(" "));
	c = null;
	d.bind("mousedown", function () {
		if ($(this).attr("disabled") === true || $(this).attr("disabled") == "true") {
			return false;
		}
		$(this).siblings("div.GF-VTab-item").removeClass("VTab-active");
		$(this).addClass("VTab-active");
		if (b.click) {
			"function" === typeof b.click ? b.click() : eval(b.click);
		}
	}).hover(function () {
		$(this).addClass("VTab-hover");
	}, function () {
		$(this).removeClass("VTab-hover");
	});
	d.show();
	b.isopen == "false" || b.isopen == false || d.mousedown();
	d = d = null;
}, removeHTab:function (a, b) {
	if (!document.getElementById(b)) {
		return false;
	}
	var c = $("#" + b), d = false;
	if (c.hasClass("HTab-active")) {
		d = true;
	}
	c.unbind("mousedown").html("");
	c.parent("td").html("").remove();
	d && $("#" + a).find("td.HTabNav-td").first().find("div:first").mousedown();
}, removeVTab:function (a, b) {
	if (!document.getElementById(b)) {
		return false;
	}
	var c = $("#" + b), d = false;
	if (c.hasClass("VTab-active")) {
		d = true;
	}
	c.unbind("mousedown").html("").remove();
	d && $("#" + a).find("div.GF-VTab-item").first().mousedown();
}, init:function () {
	$("div.GF-HTab-item").bind("mousedown", function () {
		if ($(this).attr("disabled") === true || $(this).attr("disabled") == "true") {
			return false;
		}
		$(this).parent("td").siblings().find("div:first").removeClass("HTab-active");
		$(this).addClass("HTab-active");
		var a = $(this).attr("click");
		a && a !== "" && eval(a);
	}).hover(function () {
		$(this).addClass("HTab-hover");
	}, function () {
		$(this).removeClass("HTab-hover");
	});
	$("div.GF-VTab-item").bind("mousedown", function () {
		if ($(this).attr("disabled") === true || $(this).attr("disabled") == "true") {
			return false;
		}
		$(this).siblings("div.GF-VTab-item").removeClass("VTab-active");
		$(this).addClass("VTab-active");
		var a = $(this).attr("click");
		a && a !== "" && eval(a);
	}).hover(function () {
		$(this).addClass("VTab-hover");
	}, function () {
		$(this).removeClass("VTab-hover");
	});
	$("div.GF-HTabNavigator").each(function () {
		$(this).find("div.GF-HTab-item[isopen='true']:first").mousedown();
	});
	$("div.GF-VTabNavigator").each(function () {
		$(this).find("div.GF-VTab-item[isopen='true']:first").mousedown();
	});
}};
$(function () {
	TabNavigator.init();
});
var _isIE_ = navigator.userAgent.indexOf("MSIE") != -1, _isIE6_ = navigator.userAgent.indexOf("MSIE 6.0") != -1, _isIE8_ = !!window.XDomainRequest && !!document.documentMode;
if (_isIE_) {
	try {
		document.execCommand("BackgroundImageCache", false, true);
	}
	catch (e$$19) {
	}
}
var $id = function (a) {
	return typeof a == "string" ? document.getElementById(a) : a;
};
Array.prototype.remove = function (a, b) {
	if (b) {
		for (var c = [], d = 0; d < this.length; d++) {
			a == this[d] && c.push(this.splice(d, 1)[0]);
		}
		return c;
	}
	for (d = 0; d < this.length; d++) {
		a == this[d] && this.splice(d, 1);
	}
	return this;
};
var Drag = {obj:null, init:function (a, b, c) {
	if (c == null) {
		a.onmousedown = Drag.start;
	}
	a.root = b;
	if (isNaN(parseInt(a.root.style.left))) {
		a.root.style.left = "0px";
	}
	if (isNaN(parseInt(a.root.style.top))) {
		a.root.style.top = "0px";
	}
	a.root.onDragStart = new Function;
	a.root.onDragEnd = new Function;
	a.root.onDrag = new Function;
	if (c != null) {
		a = Drag.obj = a;
		c = Drag.fixe(c);
		b = parseInt(a.root.style.top);
		var d = parseInt(a.root.style.left);
		a.root.onDragStart(d, b, c.pageX, c.pageY);
		a.lastMouseX = c.pageX;
		a.lastMouseY = c.pageY;
		document.onmousemove = Drag.drag;
		document.onmouseup = Drag.end;
	}
}, start:function (a) {
	var b = Drag.obj = this;
	a = Drag.fixEvent(a);
	var c = parseInt(b.root.style.top), d = parseInt(b.root.style.left);
	b.root.onDragStart(d, c, a.pageX, a.pageY);
	b.lastMouseX = a.pageX;
	b.lastMouseY = a.pageY;
	document.onmousemove = Drag.drag;
	document.onmouseup = Drag.end;
	return false;
}, drag:function (a) {
	a = Drag.fixEvent(a);
	var b = Drag.obj, c = a.pageY, d = a.pageX, e = parseInt(b.root.style.top), f = parseInt(b.root.style.left);
	document.all ? Drag.obj.setCapture() : a.preventDefault();
	f = f + d - b.lastMouseX;
	e = e + (c - b.lastMouseY);
	b.root.style.left = f + "px";
	b.root.style.top = e + "px";
	b.lastMouseX = d;
	b.lastMouseY = c;
	b.root.onDrag(f, e, a.pageX, a.pageY);
	return false;
}, end:function () {
	document.all && Drag.obj.releaseCapture();
	document.onmousemove = null;
	document.onmouseup = null;
	var a = parseInt(Drag.obj.root.style.left, 10), b = parseInt(Drag.obj.root.style.top, 10), c = parseInt(document.compatMode == "BackCompat" ? document.body.clientWidth : document.documentElement.clientWidth, 10), d = parseInt(document.compatMode == "BackCompat" ? document.body.clientHeight : document.documentElement.clientHeight, 10), e = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft), f = Math.max(document.documentElement.scrollTop, document.body.scrollTop), j = parseInt(Drag.obj.root.offsetWidth, 10);
	if (a <= e - (j - 30)) {
		Drag.obj.root.style.left = e - (j - 30) + "px";
	} else {
		if (a >= c + e - 30) {
			Drag.obj.root.style.left = c + e - 30 + "px";
		}
	}
	if (b <= 0 || b <= f) {
		Drag.obj.root.style.top = f > 0 ? f + "px" : "0px";
	} else {
		if (b >= d + f) {
			Drag.obj.root.style.top = d + f - 35 + "px";
		}
	}
	Drag.obj.root.onDragEnd(a, b);
	Drag.obj = null;
}, fixEvent:function (a) {
	var b = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft), c = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
	if (typeof a == "undefined") {
		a = window.event;
	}
	if (typeof a.layerX == "undefined") {
		a.layerX = a.offsetX;
	}
	if (typeof a.layerY == "undefined") {
		a.layerY = a.offsetY;
	}
	if (typeof a.pageX == "undefined") {
		a.pageX = a.clientX + b - document.body.clientLeft;
	}
	if (typeof a.pageY == "undefined") {
		a.pageY = a.clientY + c - document.body.clientTop;
	}
	return a;
}}, GC = {removeNode:_isIE_ ? function () {
	var a;
	return function (b, c) {
		if (b && b.tagName != "BODY") {
			a = a || (c || document).createElement("div");
			a.appendChild(b);
			a.innerHTML = "";
		}
	};
}() : function (a) {
	a && a.parentNode && a.tagName != "BODY" && a.parentNode.removeChild(a);
}}, $topWindow = function () {
	for (var a = window; a.parent && a.parent != a; ) {
		try {
			if (a.parent.document.domain != document.domain) {
				break;
			}
		}
		catch (b) {
			break;
		}
		a = a.parent;
	}
	return a;
}, $bodyDimensions = function (a) {
	var b = (a || window).document;
	a = b.compatMode == "BackCompat" ? b.body.clientWidth : b.documentElement.clientWidth;
	var c = b.compatMode == "BackCompat" ? b.body.clientHeight : b.documentElement.clientHeight, d = Math.max(b.documentElement.scrollLeft, b.body.scrollLeft), e = Math.max(b.documentElement.scrollTop, b.body.scrollTop), f = Math.max(b.documentElement.scrollWidth, b.body.scrollWidth);
	b = Math.max(b.documentElement.scrollHeight, b.body.scrollHeight);
	return {clientWidth:a, clientHeight:c, scrollLeft:d, scrollTop:e, scrollWidth:f, scrollHeight:b, width:Math.max(f, a), height:Math.max(b, c)};
}, fadeEffect = function (a, b, c, d, e) {
	if (!a.effect) {
		a.effect = {fade:0, move:0, size:0};
	}
	clearInterval(a.effect.fade);
	d = d || 20;
	a.effect.fade = setInterval(function () {
		b = b < c ? Math.min(b + d, c) : Math.max(b - d, c);
		a.style.opacity = b / 100;
		a.style.filter = "alpha(opacity=" + b + ")";
		if (b == c) {
			clearInterval(a.effect.fade);
			e && e.call(a);
		}
	}, 20);
}, topWin = $topWindow(), topDoc = topWin.document, Dialog = function () {
	this.OnLoad = this.URL = this.Height = this.Width = this.ID = null;
	this.InvokeElementId = this.InnerHtml = "";
	this.Left = this.Top = null;
	this.Title = "";
	this.CancelEvent = this.OKEvent = null;
	this.ShowButtonRow = false;
	this.MessageIcon = "window.gif";
	this.Message = this.MessageTitle = "";
	this.ShowMessageRow = false;
	this.Drag = this.Modal = true;
	this.AutoClose = null;
	this.ShowCloseButton = true;
	this.Animator = false;
	this.innerDoc = this.innerWin = this.innerFrame = this.opener = this.parentWindow = this.bgDiv = this.dialogDiv = null;
	this.zindex = 9999;
	this.okButton = this.cancelButton = null;
	if (arguments.length > 0 && typeof arguments[0] == "string") {
		this.ID = arguments[0];
	} else {
		arguments.length > 0 && typeof arguments[0] == "object" && Dialog.setOptions(this, arguments[0]);
	}
	if (!this.ID) {
		this.ID = topWin.Dialog._Array.length + "";
	}
};
Dialog._Array = [];
Dialog.bgDiv = null;
Dialog.setOptions = function (a, b) {
	if (b) {
		for (var c in b) {
			a[c] = b[c];
		}
	}
};
Dialog.attachBehaviors = function () {
	if (_isIE_) {
		document.attachEvent("onkeydown", Dialog.onKeyDown);
		window.attachEvent("onresize", Dialog.resetPosition);
	} else {
		document.addEventListener("keydown", Dialog.onKeyDown, false);
		window.addEventListener("resize", Dialog.resetPosition, false);
	}
};
Dialog.prototype.attachBehaviors = function () {
	this.Drag && topWin.Drag && topWin.Drag.init(topWin.$id("_Draghandle_" + this.ID), topWin.$id("_DialogDiv_" + this.ID));
	if (!_isIE_ && this.URL) {
		var a = this;
		topWin.$id("_DialogDiv_" + this.ID).onDragStart = function () {
			topWin.$id("_Covering_" + a.ID).style.display = "block";
		};
		topWin.$id("_DialogDiv_" + this.ID).onDragEnd = function () {
			topWin.$id("_Covering_" + a.ID).style.display = "none";
		};
	}
};
Dialog.prototype.displacePath = function () {
	if (this.URL.substr(0, 7) == "http://" || this.URL.substr(0, 1) == "/" || this.URL.substr(0, 11) == "javascript:") {
		return this.URL;
	} else {
		var a = this.URL, b = window.location.href;
		for (b = b.substring(0, b.lastIndexOf("/")); a.indexOf("../") >= 0; ) {
			a = a.substring(3);
			b = b.substring(0, b.lastIndexOf("/"));
		}
		return b + "/" + a;
	}
};
Dialog.prototype.setPosition = function () {
	var a = $bodyDimensions(topWin), b = this.Top, c = this.Left, d = this.getDialogDiv();
	if (this.Top == null) {
		b = (a.clientHeight - d.offsetHeight) / 3 + a.scrollTop;
	}
	if (this.Left == null) {
		c = (a.clientWidth - d.offsetWidth) / 2 + a.scrollLeft;
	}
	if (typeof this.Top == "string" && this.Top.substring(this.Top.length - 1, this.Top.length) == "%") {
		b = this.Top.substring(0, this.Top.length - 1) * 0.01;
		b = (a.clientHeight - d.offsetHeight) * b + a.scrollTop;
	}
	if (typeof this.Left == "string" && this.Left.substring(this.Left.length - 1, this.Left.length) == "%") {
		c = this.Left.substring(0, this.Left.length - 1) * 0.01;
		c = (a.clientWidth - d.offsetWidth) * c + a.scrollLeft;
	}
	d.style.top = Math.round(b) + "px";
	d.style.left = Math.round(c) + "px";
};
Dialog.setBgDivSize = function () {
	var a = $bodyDimensions(topWin);
	if (Dialog.bgDiv) {
		Dialog.bgDiv.style.width = a.scrollWidth + "px";
		if (_isIE6_) {
			Dialog.bgDiv.style.height = a.clientHeight + "px";
			Dialog.bgDiv.style.top = a.scrollTop + "px";
			Dialog.bgDiv.childNodes[0].style.display = "none";
			Dialog.bgDiv.childNodes[0].style.display = "";
		} else {
			Dialog.bgDiv.style.height = a.scrollHeight + "px";
		}
	}
};
Dialog.resetPosition = function () {
	Dialog.setBgDivSize();
	for (var a = 0, b = topWin.Dialog._Array.length; a < b; a++) {
		topWin.Dialog._Array[a].setPosition();
	}
};
Dialog.prototype.create = function () {
	var a = $bodyDimensions(topWin);
	if (typeof this.OKEvent == "function") {
		this.ShowButtonRow = true;
	}
	if (!this.Width) {
		this.Width = 600;
	}
	if (!this.Height) {
		this.Height = Math.round(this.Width / 2);
	}
	if (this.MessageTitle || this.Message) {
		this.ShowMessageRow = true;
	}
	var b = this.Height + 33 + 13 + (this.ShowButtonRow ? 40 : 0) + (this.ShowMessageRow ? 50 : 0);
	if (this.Width + 13 + 13 > a.clientWidth) {
		this.Width = Math.round(a.clientWidth - 26);
	}
	if (b > a.clientHeight) {
		this.Height = Math.round(a.clientHeight - 46 - (this.ShowButtonRow ? 40 : 0) - (this.ShowMessageRow ? 50 : 0));
	}
	a = "  <table id=\"_DialogTable_" + this.ID + "\" width=\"" + (this.Width + 26) + "\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" onselectstart=\"return false;\" oncontextmenu=\"return false;\" style=\"-moz-user-select:none;\">    <tr id=\"_Draghandle_" + this.ID + "\" style=\"" + (this.Drag ? "cursor: move;" : "") + "\">      <td width=\"13\" height=\"33\" class=\"dlg-lt\"><i>&nbsp;</i></td>      <td height=\"33\" class=\"dlg-ct\"><div id=\"_Title_" + this.ID + "\" class=\"dlg-title\">" + this.Title + "</div>        <a href=\"javascript:void(0);\" onclick=\"Dialog.getInstance('" + this.ID + "').cancelButton.onclick.apply(Dialog.getInstance('" + this.ID + "').cancelButton,[]);return false;\" onmousedown=\"return false;\" class=\"dlg-close\" style=\"" + (this.ShowCloseButton ? "" : "display:none;") + "\"></a></td>      <td width=\"13\" height=\"33\" class=\"dlg-rt\"><i><a id=\"_forTab_" + this.ID + "\" href=\"#;\"></a></i></td>    </tr>    <tr valign=\"top\">      <td width=\"13\" class=\"dlg-ll\"><i>&nbsp;</i></td>      <td align=\"center\" valign=\"top\" class=\"dlg-body\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" bgcolor=\"#ffffff\">          <tr id=\"_MessageRow_" + this.ID + "\" style=\"" + (this.ShowMessageRow ? "" : "display:none") + "\">            <td valign=\"top\" height=\"50\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"dlg-help-panel\" id=\"_MessageTable_" + this.ID + "\">                <tr>                  <td width=\"50\" height=\"50\" align=\"center\"><div class=\"dlg-help-ico\" id=\"_MessageIcon_" + this.ID + "\">&nbsp;</div></td>                  <td align=\"left\"><div id=\"_MessageTitle_" + this.ID + "\" class=\"dlg-help-title\">" + this.MessageTitle + "</div>                    <div id=\"_Message_" + this.ID + "\" class=\"dlg-help-info\">" + this.Message + "</div></td>                </tr>              </table></td>          </tr>          <tr>            <td valign=\"top\" align=\"center\"><div id=\"_Container_" + this.ID + "\" style=\"position: relative; width: " + this.Width + "px; height: " + this.Height + "px;\">                <div class=\"dlg-drag-cover\" id=\"_Covering_" + this.ID + "\" style=\"display:none;\">&nbsp;</div>\t";
	b = this.InnerHtml ? this.InnerHtml : this.URL ? "<iframe width=\"100%\" height=\"100%\" frameborder=\"0\" style=\"border:none 0;\" allowtransparency=\"true\" id=\"_DialogFrame_" + this.ID + "\" src=\"" + this.displacePath() + "\"></iframe>" : "";
	a = a + b + "              </div></td>          </tr>          <tr id=\"_ButtonRow_" + this.ID + "\" style=\"" + (this.ShowButtonRow ? "" : "display:none") + "\">            <td height=\"30\"><div id=\"_DialogButtons_" + this.ID + "\" class=\"dlg-btn-panel\">                <input type=\"button\" class=\"dlg-btn\" value=\"\u786e\u5b9a\" onmouseover=\"this.className='dlg-btn dlg-btn-hover'\" onmouseout=\"this.className='dlg-btn'\" id=\"_DlgOKBtn_" + this.ID + "\"/>                <input type=\"button\" class=\"dlg-btn\" value=\"\u53d6\u6d88\" onmouseover=\"this.className='dlg-btn dlg-btn-hover'\" onmouseout=\"this.className='dlg-btn'\" onclick=\"Dialog.getInstance('" + this.ID + "').close();\" id=\"_DlgCancelBtn_" + this.ID + "\"/>              </div></td>          </tr>        </table></td>      <td width=\"13\" class=\"dlg-lr\"><i>&nbsp;</i></td>    </tr>    <tr>      <td width=\"13\" height=\"13\" class=\"dlg-bl\"><i>&nbsp;</i></td>      <td class=\"dlg-bc\"><i>&nbsp;</i></td>      <td width=\"13\" height=\"13\" class=\"dlg-br\"><i><a onfocus='$id(\"_forTab_" + this.ID + "\").focus();' href=\"#;\"></a></i></td>    </tr>  </table></div>";
	b = topWin.$id("_DialogDiv_" + this.ID);
	if (!b) {
		b = topDoc.createElement("div");
		b.id = "_DialogDiv_" + this.ID;
		topDoc.getElementsByTagName("BODY")[0].appendChild(b);
	}
	b.style.display = "none";
	b.style.position = "absolute";
	b.style.left = "-9999px";
	b.style.top = "-9999px";
	b.innerHTML = a;
	if (this.InvokeElementId) {
		a = $id(this.InvokeElementId);
		a.style.position = "";
		a.style.display = "";
		if (_isIE_) {
			b = topDoc.createElement("div");
			b.innerHTML = a.outerHTML;
			a.outerHTML = "";
			topWin.$id("_Covering_" + this.ID).parentNode.appendChild(b);
		} else {
			topWin.$id("_Covering_" + this.ID).parentNode.appendChild(a);
		}
	}
	this.opener = this.parentWindow = window;
	if (this.URL) {
		if (topWin.$id("_DialogFrame_" + this.ID)) {
			this.innerFrame = topWin.$id("_DialogFrame_" + this.ID);
		}
		var c = this;
		innerFrameOnload = function () {
			try {
				c.innerWin = c.innerFrame.contentWindow;
				c.innerWin.parentDialog = c;
				c.innerWin.ownerDialog = c;
				c.innerDoc = c.innerWin.document;
				if (!c.Title && c.innerDoc && c.innerDoc.title) {
					if (c.innerDoc.title) {
						topWin.$id("_Title_" + c.ID).innerHTML = c.innerDoc.title;
					}
				}
			}
			catch (d) {
			}
			typeof c.OnLoad == "function" && c.OnLoad(c);
		};
		if (this.innerFrame.attachEvent) {
			this.innerFrame.attachEvent("onload", innerFrameOnload);
		} else {
			this.innerFrame.onload = innerFrameOnload;
		}
	}
	topWin.$id("_DialogDiv_" + this.ID).dialogId = this.ID;
	topWin.$id("_DialogDiv_" + this.ID).dialogInstance = this;
	this.attachBehaviors();
	this.okButton = topWin.$id("_DlgOKBtn_" + this.ID);
	this.cancelButton = topWin.$id("_DlgCancelBtn_" + this.ID);
	b = null;
};
Dialog.prototype.setSize = function (a, b) {
	if (a && +a > 20) {
		this.Width = +a;
		topWin.$id("_DialogTable_" + this.ID).width = this.Width + 26;
		topWin.$id("_Container_" + this.ID).style.width = this.Width + "px";
	}
	if (b && +b > 10) {
		this.Height = +b;
		topWin.$id("_Container_" + this.ID).style.height = this.Height + "px";
	}
	this.setPosition();
};
Dialog.prototype.show = function () {
	var a = Dialog.getBgdiv();
	this.create();
	var b = this.getDialogDiv();
	this.zindex = b.style.zIndex = parseInt(Dialog.bgDiv.style.zIndex, 10) + 1;
	if (topWin.Dialog._Array.length > 0) {
		this.zindex = b.style.zIndex = parseInt(topWin.Dialog._Array[topWin.Dialog._Array.length - 1].zindex, 10) + 2;
	} else {
		var c = topDoc.getElementsByTagName(topDoc.compatMode == "BackCompat" ? "BODY" : "HTML")[0];
		c.styleOverflow = c.style.overflow;
		c.style.overflow = "hidden";
		a.style.display = "none";
	}
	topWin.Dialog._Array.push(this);
	if (this.Modal) {
		a.style.zIndex = parseInt(topWin.Dialog._Array[topWin.Dialog._Array.length - 1].zindex, 10) - 1;
		Dialog.setBgDivSize();
		if (a.style.display == "none") {
			if (this.Animator) {
				a.style.opacity = 0;
				a.style.filter = "alpha(opacity=0)";
				a.style.display = "";
				fadeEffect(a, 0, 20, _isIE6_ ? 20 : 10);
			} else {
				a.style.display = "block";
			}
		}
	}
	b.style.display = "block";
	this.setPosition();
	if (this.CancelEvent) {
		var d = this;
		this.cancelButton.onclick = function () {
			d.CancelEvent(d);
		};
		this.ShowButtonRow && this.cancelButton.focus();
	}
	if (this.OKEvent) {
		this.okButton.onclick = this.OKEvent;
		this.ShowButtonRow && this.okButton.focus();
	}
	this.AutoClose && this.AutoClose > 0 && this.autoClose();
	this.opened = true;
	b = a = null;
};
Dialog.prototype.close = function () {
	var a = topWin.$id("_DialogDiv_" + this.ID);
	a.style.display = "none";
	var b = topWin.$id("_DialogBGDiv"), c = null;
	if (this == topWin.Dialog._Array[topWin.Dialog._Array.length - 1]) {
		c = topWin.Dialog._Array.pop();
	} else {
		topWin.Dialog._Array.remove(this);
	}
	if (this.InvokeElementId) {
		var d = topWin.$id(this.InvokeElementId);
		d.style.display = "none";
		if (_isIE_) {
			var e = document.createElement("div");
			e.innerHTML = d.outerHTML;
			d.outerHTML = "";
			document.getElementsByTagName("BODY")[0].appendChild(e);
		} else {
			document.getElementsByTagName("BODY")[0].appendChild(d);
		}
	}
	if (topWin.Dialog._Array.length > 0) {
		if (this.Modal && c) {
			b.style.zIndex = parseInt(topWin.Dialog._Array[topWin.Dialog._Array.length - 1].zindex, 10) - 1;
		}
	} else {
		b.style.zIndex = this.zindex;
		b.style.display = "none";
		b = topDoc.getElementsByTagName(topDoc.compatMode == "BackCompat" ? "BODY" : "HTML")[0];
		if (b.styleOverflow != undefined) {
			b.style.overflow = b.styleOverflow;
		}
	}
	a.dialogInstance = null;
	if (this.innerFrame) {
		this.innerFrame.style.display = "none";
		this.innerFrame.src = "about:blank";
		_isIE_ && this.innerFrame.detachEvent("onload", innerFrameOnload);
	}
	a.onDragStart = null;
	a.onDragEnd = null;
	topWin.$id("_Draghandle_" + this.ID).onmousedown = null;
	topWin.$id("_Draghandle_" + this.ID).root = null;
	if (this.CancelEvent) {
		this.cancelButton.onclick = null;
	}
	if (this.OKEvent) {
		this.okButton.onclick = null;
	}
	topWin.$id("_Container_" + this.ID).innerHTML = "";
	a.innerHTML = "";
	GC.removeNode(a, topDoc);
	_isIE_ && CollectGarbage();
	this.bgDiv = this.innerFrame = null;
	this.closed = true;
};
Dialog.prototype.autoClose = function () {
	if (this.closed) {
		clearTimeout(this._closeTimeoutId);
	} else {
		this.AutoClose -= 1;
		topWin.$id("_Title_" + this.ID).innerHTML = this.AutoClose + " \u79d2\u540e\u81ea\u52a8\u5173\u95ed";
		if (this.AutoClose <= 0) {
			this.close();
		} else {
			var a = this;
			this._closeTimeoutId = setTimeout(function () {
				a.autoClose();
			}, 1000);
		}
	}
};
Dialog.getInstance = function (a) {
	(a = topWin.$id("_DialogDiv_" + a)) || alert("\u6ca1\u6709\u53d6\u5230\u5bf9\u5e94ID\u7684\u5f39\u51fa\u6846\u9875\u9762\u5bf9\u8c61");
	return a.dialogInstance;
};
Dialog.prototype.addButton = function (a, b, c) {
	var d = null;
	topWin.$id("_ButtonRow_" + this.ID).style.display = "";
	this.ShowButtonRow = true;
	try {
		d = topDoc.createElement("input");
		d.id = "_Button_" + this.ID + "_" + a;
		d.type = "button";
		d.className = "dlg-btn";
		d.value = b;
		d.onclick = c;
		d.onmouseover = function () {
			this.className = "dlg-btn dlg-btn-hover";
		};
		d.onmouseout = function () {
			this.className = "dlg-btn";
		};
		var e = topWin.$id("_DialogButtons_" + this.ID).getElementsByTagName("INPUT")[0];
		e.parentNode.insertBefore(d, e);
		return d;
	}
	catch (f) {
	}
	finally {
		d = null;
	}
};
Dialog.prototype.removeButton = function (a) {
	var b = topWin.$id("_DialogButtons_" + this.ID).getElementsByTagName("INPUT")[0];
	b.onmouseover = null;
	b.onmouseout = null;
	b.onclick = null;
	b.parentNode.removeChild(a);
};
Dialog.getBgdiv = function () {
	if (Dialog.bgDiv) {
		return Dialog.bgDiv;
	}
	var a = topWin.$id("_DialogBGDiv");
	if (!a) {
		a = topDoc.createElement("div");
		a.id = "_DialogBGDiv";
		topDoc.getElementsByTagName("BODY")[0].appendChild(a);
		a.style.display = "none";
		a.oncontextmenu = function () {
			return false;
		};
		a.style.cssText = "position:absolute;left:0px;top:0px;width:100%;height:100%;background-color:#000000;opacity:0.2;filter:alpha(opacity=20);z-index:9999;";
		a.innerHTML = _isIE6_ ? "<iframe src=\"javascript:void(0);\" style=\"width:100%;height:100%;filter:alpha(opacity=0);\" frameborder=\"0\" width=\"100%\" height=\"100%\" scrolling=\"no\"></iframe>" : "&nbsp;";
	}
	Dialog.bgDiv = a;
	a = null;
	return Dialog.bgDiv;
};
Dialog.prototype.getDialogDiv = function () {
	var a = topWin.$id("_DialogDiv_" + this.ID);
	a || alert("\u83b7\u53d6\u5f39\u51fa\u5c42\u9875\u9762\u5bf9\u8c61\u51fa\u9519\uff01");
	return a;
};
Dialog.onKeyDown = function (a) {
	if (a.keyCode == 9) {
		if (topWin.Dialog._Array.length > 0) {
			if (a.stopPropagation) {
				a.stopPropagation();
			} else {
				a.cancelBubble = true;
			}
			if (a.preventDefault) {
				a.preventDefault();
			} else {
				a.returnValue = false;
			}
			return false;
		}
	}
	a.keyCode == 27 && Dialog.close();
	if (a.keyCode == 37) {
		if (topWin.Dialog._Array.length > 0) {
			var b = topWin.Dialog._Array[topWin.Dialog._Array.length - 1];
			b.okButton && b.okButton.focus();
			delete b;
		}
	}
	if (a.keyCode == 39) {
		if (topWin.Dialog._Array.length > 0) {
			b = topWin.Dialog._Array[topWin.Dialog._Array.length - 1];
			b.cancelButton && b.cancelButton.focus();
			delete b;
		}
	}
};
Dialog.close = function (a) {
	var b = null;
	if (a && a != "") {
		Dialog.getInstance(a).close();
	} else {
		if (topWin.Dialog._Array.length > 0) {
			b = topWin.Dialog._Array[topWin.Dialog._Array.length - 1];
			b.cancelButton.onclick.apply(b.cancelButton, []);
		}
	}
};
Dialog.alert = function (a, b, c, d) {
	var e = new Dialog({Width:c || 320, Height:d || 100});
	e.ShowButtonRow = true;
	e.ShowCloseButton = false;
	e.Animator = false;
	e.Title = "\u7cfb\u7edf\u63d0\u793a";
	e.CancelEvent = function () {
		e.close();
		b && b();
	};
	e.InnerHtml = createAlertsHtml(e.ID, "dlg-alert-ico", a);
	e.show();
	e.okButton.style.display = "none";
	e.CancelEvent = function () {
		e.close();
		b && b();
	};
	e.cancelButton.value = "\u786e\u5b9a";
	e.cancelButton.focus();
};
Dialog.confirm = function (a, b, c, d, e) {
	var f = new Dialog({Width:d || 320, Height:e || 100});
	f.ShowButtonRow = true;
	f.ShowCloseButton = false;
	f.Animator = false;
	f.Title = "\u7cfb\u7edf\u8be2\u95ee";
	f.OKEvent = function () {
		f.close();
		b && b();
	};
	f.CancelEvent = function () {
		f.close();
		c && c();
	};
	f.InnerHtml = createAlertsHtml(f.ID, "dlg-confirm-ico", a);
	f.show();
	f.okButton.value = "\u662f";
	f.cancelButton.value = "\u5426";
	f.okButton.focus();
};
Dialog.warning = function (a, b, c, d) {
	var e = new Dialog({Width:c || 320, Height:d || 100});
	e.ShowButtonRow = true;
	e.ShowCloseButton = false;
	e.Animator = false;
	e.Title = "\u7cfb\u7edf\u8b66\u544a";
	e.CancelEvent = function () {
		e.close();
		b && b();
	};
	e.InnerHtml = createAlertsHtml(e.ID, "dlg-warning-ico", a);
	e.show();
	e.okButton.style.display = "none";
	e.cancelButton.value = "\u786e\u5b9a";
	e.cancelButton.focus();
};
Dialog.success = function (a, b, c, d) {
	var e = new Dialog({Width:c || 320, Height:d || 100});
	e.ShowButtonRow = true;
	e.ShowCloseButton = false;
	e.Animator = false;
	e.Title = "\u64cd\u4f5c\u6210\u529f";
	e.CancelEvent = function () {
		e.close();
		b && b();
	};
	e.InnerHtml = createAlertsHtml(e.ID, "dlg-success-ico", a);
	e.show();
	e.okButton.style.display = "none";
	e.cancelButton.value = "\u786e\u5b9a";
	e.cancelButton.focus();
};
Dialog.error = function (a, b, c, d) {
	var e = new Dialog({Width:c || 320, Height:d || 100});
	e.ShowButtonRow = true;
	e.ShowCloseButton = false;
	e.Animator = false;
	e.Title = "\u7cfb\u7edf\u9519\u8bef";
	e.CancelEvent = function () {
		e.close();
		b && b();
	};
	e.InnerHtml = createAlertsHtml(e.ID, "dlg-error-ico", a);
	e.show();
	e.okButton.style.display = "none";
	e.cancelButton.value = "\u786e\u5b9a";
	e.cancelButton.focus();
};
Dialog.open = function (a) {
	var b = null;
	b = new Dialog(a);
	b.show();
	return b;
};
Dialog.opener = function () {
	return window.ownerDialog.opener;
};
function createAlertsHtml(a, b, c) {
	return "<table width=\"100%\" height=\"100%\" border=\"0\" align=\"left\" cellpadding=\"10\" cellspacing=\"0\">\t\t<tr><td nowrap align=\"center\" width=\"48\"><div id=\"Icon_" + a + "\" class=\"" + b + "\">&nbsp;</div></td>\t\t\t<td align=\"left\" id=\"Message_" + a + "\"><div class=\"dlg-alerts-info\">" + c + "</div></td></tr>\t</table>";
}
_isIE_ ? window.attachEvent("onload", Dialog.attachBehaviors) : window.addEventListener("load", Dialog.attachBehaviors, false);
Object.extend = function () {
	var a = arguments[0] || {}, b = 1, c = arguments.length, d = false, e;
	if (typeof a === "boolean") {
		d = a;
		a = arguments[1] || {};
		b = 2;
	}
	if (typeof a !== "object" && "function" !== typeof a) {
		a = {};
	}
	if (c == b) {
		a = this;
		--b;
	}
	for (; b < c; b++) {
		if ((e = arguments[b]) != null) {
			for (var f in e) {
				var j = a[f], n = e[f];
				if (a !== n) {
					if (d && n && typeof n === "object" && !n.nodeType) {
						a[f] = Object.extend(d, j || (n.length != null ? [] : {}), n);
					} else {
						if (n !== undefined) {
							a[f] = n;
						}
					}
				}
			}
		}
	}
	return a;
};
function WebFXTreePersistence() {
}
var _p = WebFXTreePersistence.prototype;
_p.getExpanded = function () {
	return false;
};
_p.setExpanded = function () {
};
function WebFXCookie() {
}
_p = WebFXCookie.prototype;
_p.setCookie = function (a, b, c) {
	var d = "";
	if (typeof c == "number") {
		d = new Date;
		d.setTime(d.getTime() + c * 24 * 60 * 60 * 1000);
		d = "; expires=" + d.toGMTString();
	}
	document.cookie = a + "=" + escape(b) + d + "; path=/";
};
_p.getCookie = function (a) {
	a = (new RegExp("(;|^)[^;]*(" + a + ")=([^;]*)(;|$)")).exec(document.cookie);
	return a != null ? unescape(a[3]) : null;
};
_p.removeCookie = function (a) {
	this.setCookie(a, "", -1);
};
function WebFXTreeCookiePersistence() {
	this._openedMap = {};
	this._cookies = new WebFXCookie;
	var a = this._cookies.getCookie(this.cookieName);
	if (a) {
		a = a.split("+");
		for (var b = a.length - 1; b >= 0; b--) {
			this._openedMap[a[b]] = true;
		}
	}
}
_p = WebFXTreeCookiePersistence.prototype = new WebFXTreePersistence;
_p.cookieName = "webfx-tree-cookie-persistence";
_p.getExpanded = function (a) {
	return a.id in this._openedMap;
};
_p.setExpanded = function (a, b) {
	if (this.getExpanded(a) != b) {
		if (b) {
			this._openedMap[a.id] = true;
		} else {
			delete this._openedMap[a.id];
		}
		var c = [], d = 0;
		for (var e in this._openedMap) {
			c[d++] = e;
		}
		this._cookies.setCookie(this.cookieName, c.join("+"));
	}
};
var arrayHelper = {indexOf:function (a, b) {
	for (var c = 0; c < a.length; c++) {
		if (a[c] == b) {
			return c;
		}
	}
	return -1;
}, insertBefore:function (a, b, c) {
	c = this.indexOf(a, c);
	c == -1 ? a.push(b) : a.splice(c, 0, b);
}, remove:function (a, b) {
	var c = this.indexOf(a, b);
	c != -1 && a.splice(c, 1);
}}, webFXTreeConfig = {rootIcon:"images/tree/root.gif", openRootIcon:"images/tree/openroot.gif", folderIcon:"images/tree/folder.gif", openFolderIcon:"images/tree/openfolder.gif", fileIcon:"images/tree/file.gif", iIcon:"images/tree/I.gif", lIcon:"images/tree/L.gif", lMinusIcon:"images/tree/Lminus.gif", lPlusIcon:"images/tree/Lplus.gif", tIcon:"images/tree/T.gif", tMinusIcon:"images/tree/Tminus.gif", tPlusIcon:"images/tree/Tplus.gif", plusIcon:"images/tree/plus.gif", minusIcon:"images/tree/minus.gif", blankIcon:"images/tree/blank.gif", loadingIcon:"images/tree/loading.gif", errorIcon:"images/tree/error.gif", defaultText:" ", loadingText:"\u6b63\u5728\u52a0\u8f7d...", defaultAction:"javascript:void(0);", defaultBehavior:"classic", usePersistence:false, setImagePath:function (a) {
	if (a) {
		if (a.charAt(a.length - 1) != "/") {
			a += "/";
		}
	} else {
		a = "images/";
	}
	this.rootIcon = a + "root.gif";
	this.openRootIcon = a + "openroot.gif";
	this.folderIcon = a + "folder.gif";
	this.openFolderIcon = a + "openfolder.gif";
	this.fileIcon = a + "file.gif";
	this.iIcon = a + "I.gif";
	this.lIcon = a + "L.gif";
	this.lMinusIcon = a + "Lminus.gif";
	this.lPlusIcon = a + "Lplus.gif";
	this.tIcon = a + "T.gif";
	this.tMinusIcon = a + "Tminus.gif";
	this.tPlusIcon = a + "Tplus.gif";
	this.plusIcon = a + "plus.gif";
	this.minusIcon = a + "minus.gif";
	this.blankIcon = a + "blank.gif";
	this.loadingIcon = a + "loading.gif";
	this.errorIcon = a + "error.gif";
}, target:null, setTarget:function (a) {
	if (a) {
		this.target = a;
	}
}, cascadeChildCheck:true, allChildCheckToParent:false, allChildUnCheckToParent:true, checkEvent:false, onTreeContext:false}, webFXTreeHandler = {ie:/msie/i.test(navigator.userAgent), opera:/opera/i.test(navigator.userAgent), idCounter:0, idPrefix:"wfxt-", checkboxPrefix:"CB-", radioPrefix:"RD-", getUniqueId:function () {
	return this.idPrefix + this.idCounter++;
}, all:{}, getNodeById:function (a) {
	return this.all[a];
}, addNode:function (a) {
	this.all[a.id] = a;
}, removeNode:function (a) {
	delete this.all[a.id];
}, handleEvent:function (a) {
	for (var b = a.target || a.srcElement; b != null && !this.all[b.id]; ) {
		b = b.parentNode;
	}
	if (b == null) {
		return false;
	}
	b = this.all[b.id];
	if (typeof b["_on" + a.type] == "function") {
		return b["_on" + a.type](a);
	}
	return false;
}, contextMenu:function (a, b, c) {
	if (webFXTreeConfig.onTreeContext && typeof webFXTreeConfig.onTreeContext == "function") {
		webFXTreeConfig.onTreeContext(a, b, c);
		return false;
	}
}, dispose:function () {
	if (!this.disposed) {
		for (var a in this.all) {
			this.all[a].dispose();
		}
		this.disposed = true;
	}
}, htmlToText:function (a) {
	return String(a).replace(/\s+|<([^>])+>|&amp;|&lt;|&gt;|&quot;|&nbsp;/gi, this._htmlToText);
}, _htmlToText:function (a) {
	switch (a) {
	  case "&amp;":
		return "&";
	  case "&lt;":
		return "<";
	  case "&gt;":
		return ">";
	  case "&quot;":
		return "\"";
	  case "&nbsp;":
		return String.fromCharCode(160);
	  default:
		if (/\s+/.test(a)) {
			return " ";
		}
		if (/^<BR/gi.test(a)) {
			return "\n";
		}
		return "";
	}
}, textToHtml:function (a) {
	return a;
}, _textToHtml:function (a) {
	switch (a) {
	  case "&":
		return "&amp;";
	  case "<":
		return "&lt;";
	  case ">":
		return "&gt;";
	  case "\n":
		return "<BR>";
	  case "\"":
		return "&quot;";
	  default:
		return "&nbsp;";
	}
}, persistenceManager:new WebFXTreeCookiePersistence}, webFXTreeProp = {id:null, type:"normal", name:"", value:"", text:"\u672a\u77e5\u8282\u70b9", action:"javascript:void(0);", src:null, icon:"", openIcon:"", disabled:false, checked:false, target:null};
function WebFXTreeAbstractNode(a, b) {
	this.childNodes = [];
	if (a) {
		this.text = a;
	}
	if (b) {
		this.action = b;
	}
	this.id = webFXTreeHandler.getUniqueId();
	if (webFXTreeConfig.usePersistence) {
		this.open = webFXTreeHandler.persistenceManager.getExpanded(this);
	}
	webFXTreeHandler.addNode(this);
}
_p = WebFXTreeAbstractNode.prototype;
_p._selected = false;
_p.indentWidth = 18;
_p.open = false;
_p.text = webFXTreeConfig.defaultText;
_p.action = null;
_p.target = null;
_p.toolTip = null;
_p._focused = false;
_p.doChecked = function (a, b, c) {
	if (b == 0) {
		doRadioClick(this, a);
	} else {
		b == 1 && doCheckBoxClick(this, a);
	}
	_stopEvent(c);
};
_p.add = function (a, b) {
	var c, d = this.childNodes.length == 0, e = a.parentNode;
	if (b == null) {
		e != null && e.remove(a);
		c = this.getLastChild();
		this.childNodes.push(a);
	} else {
		if (b.parentNode != this) {
			throw new Error("Can only add nodes before siblings");
		}
		e != null && e.remove(a);
		arrayHelper.insertBefore(this.childNodes, a, b);
	}
	if (b) {
		if (b == this.firstChild) {
			this.firstChild = a;
		}
		a.previousSibling = b.previousSibling;
		b.previousSibling = a;
		a.nextSibling = b;
	} else {
		if (!this.firstChild) {
			this.firstChild = a;
		}
		if (this.lastChild) {
			this.lastChild.nextSibling = a;
		}
		a.previousSibling = this.lastChild;
		this.lastChild = a;
	}
	a.parentNode = this;
	if (e = this.getTree()) {
		a.tree = e;
	}
	var f = this.getDepth();
	if (f != null) {
		a.depth = f + 1;
	}
	if (this.getCreated() && !e.getSuspendRedraw()) {
		f = this.getChildrenElement();
		var j = a.create(), n = b ? b.getElement() : null;
		f.insertBefore(j, n);
		c && c.updateExpandIcon();
		if (d) {
			this.setExpanded(this.getExpanded());
			e && e.getBehavior() != "classic" && this.updateIcon();
		}
	}
	return a;
};
_p.remove = function (a) {
	if (arguments.length == 0) {
		if (this.parentNode) {
			return this.parentNode.remove(this);
		}
		return null;
	}
	var b = this.getTree(), c = b ? b.getSelected() : null;
	if (c == a || a.contains(c)) {
		if (c.getFocused()) {
			this.select();
			window.setTimeout("WebFXTreeAbstractNode._onTimeoutFocus(\"" + this.id + "\")", 10);
		} else {
			this.select();
		}
	}
	if (a.parentNode != this) {
		throw new Error("Can only remove children");
	}
	arrayHelper.remove(this.childNodes, a);
	if (this.lastChild == a) {
		this.lastChild = a.previousSibling;
	}
	if (this.firstChild == a) {
		this.firstChild = a.nextSibling;
	}
	if (a.previousSibling) {
		a.previousSibling.nextSibling = a.nextSibling;
	}
	if (a.nextSibling) {
		a.nextSibling.previousSibling = a.previousSibling;
	}
	c = a.isLastSibling();
	a.parentNode = null;
	a.tree = null;
	a.depth = null;
	if (b && this.getCreated() && !b.getSuspendRedraw()) {
		b = this.getChildrenElement();
		var d = a.getElement();
		b.removeChild(d);
		if (c) {
			(c = this.getLastChild()) && c.updateExpandIcon();
		}
		if (!this.hasChildren()) {
			b.style.display = "none";
			this.updateExpandIcon();
			this.updateIcon();
		}
	}
	return a;
};
WebFXTreeAbstractNode._onTimeoutFocus = function (a) {
	webFXTreeHandler.all[a].focus();
};
_p.getId = function () {
	return this.id;
};
_p.getTree = function () {
	throw new Error("getTree called on Abstract Node");
};
_p.getDepth = function () {
	throw new Error("getDepth called on Abstract Node");
};
_p.getCreated = function () {
	var a = this.getTree();
	return a && a.rendered;
};
_p.getParent = function () {
	return this.parentNode;
};
_p.contains = function (a) {
	if (a == null) {
		return false;
	}
	if (a == this) {
		return true;
	}
	return this.contains(a.parentNode);
};
_p.getChildren = _p.getChildNodes = function () {
	return this.childNodes;
};
_p.getFirstChild = function () {
	return this.childNodes[0];
};
_p.getLastChild = function () {
	return this.childNodes[this.childNodes.length - 1];
};
_p.getPreviousSibling = function () {
	return this.previousSibling;
};
_p.getNextSibling = function () {
	return this.nextSibling;
};
_p.hasChildren = function () {
	return this.childNodes.length > 0;
};
_p.isLastSibling = function () {
	return this.nextSibling == null;
};
_p.findChildByText = function (a, b) {
	b || (b = 0);
	for (var c = 0; c < this.childNodes.length; c++) {
		if (a instanceof RegExp && a.test(this.childNodes[c].getText()) || this.childNodes[c].getText() == a) {
			if (b == 0) {
				return this.childNodes[c];
			}
			b--;
		}
	}
	return null;
};
_p.findNodeByText = function (a, b) {
	b || (b = 0);
	if (a instanceof RegExp && a.test(this.getText()) || this.getText() == a) {
		if (b == 0) {
			return this.childNodes[d];
		}
		b--;
	}
	for (var c, d = 0; d < this.childNodes.length; d++) {
		if (c = this.childNodes[d].findNodeByText(a, b)) {
			return c;
		}
	}
	return null;
};
_p.setId = function (a) {
	var b = this.getElement();
	webFXTreeHandler.removeNode(this);
	this.id = a;
	if (b) {
		b.id = a;
	}
	webFXTreeHandler.addNode(this);
};
_p.isSelected = function () {
	return this._selected;
};
_p.select = function () {
	this._setSelected(true);
};
_p.deselect = function () {
	this._setSelected(false);
};
_p._setSelected = function (a) {
	var b = this.getTree();
	if (b) {
		if (this._selected != a) {
			this._selected = a;
			var c = false, d = b.getSelected();
			if (a && d != null && d != this) {
				var e = b._fireChange;
				c = d._focused;
				b._fireChange = false;
				d._setSelected(false);
				b._fireChange = e;
			}
			if (d = this.getRowElement()) {
				d.className = this.getRowClassName();
			}
			if (a) {
				this._setTabIndex(b.tabIndex);
				b._selectedItem = this;
				b._fireOnChange();
				b.setSelected(this);
				c && this.focus();
			} else {
				this._setTabIndex(-1);
			}
			b.getBehavior() != "classic" && this.updateIcon();
		}
	}
};
_p.getExpanded = function () {
	return this.open;
};
_p.setExpanded = function (a) {
	var b;
	this.open = a;
	var c = this.getTree();
	if (this.hasChildren()) {
		b = c ? c.getSelected() : null;
		!a && this.contains(b) && this.select();
		if (this.getElement()) {
			if (b = this.getChildrenElement()) {
				b.style.display = a ? "block" : "none";
			}
			if (b = this.getExpandIconElement()) {
				b.src = this.getExpandIconSrc();
			}
		}
		webFXTreeConfig.usePersistence && webFXTreeHandler.persistenceManager.setExpanded(this, a);
	} else {
		if (b = this.getChildrenElement()) {
			b.style.display = "none";
		}
	}
	c && c.getBehavior() == "classic" && this.updateIcon();
};
_p.toggle = function () {
	this.setExpanded(!this.getExpanded());
};
_p.expand = function () {
	this.setExpanded(true);
};
_p.collapse = function () {
	this.setExpanded(false);
};
_p.collapseChildren = function () {
	for (var a = this.childNodes, b = 0; b < a.length; b++) {
		a[b].collapseAll();
	}
};
_p.collapseAll = function () {
	this.collapseChildren();
	this.collapse();
};
_p.expandChildren = function () {
	for (var a = this.childNodes, b = 0; b < a.length; b++) {
		a[b].expandAll();
	}
};
_p.expandAll = function () {
	this.expandChildren();
	this.expand();
};
_p.reveal = function () {
	var a = this.getParent();
	if (a) {
		a.setExpanded(true);
		a.reveal();
	}
};
_p.openPath = function (a, b, c) {
	if (a == "") {
		b && this.select();
		c && window.setTimeout("WebFXTreeAbstractNode._onTimeoutFocus(\"" + this.id + "\")", 10);
	} else {
		var d = a.split("/"), e = d.slice(1).join("/");
		d = this.getTree();
		if (a.charAt(0) == "/") {
			if (d) {
				d.openPath(e, b, c);
			} else {
				throw "Invalid path";
			}
		} else {
			this.setExpanded(true);
			d = a.split("/");
			a = this.findChildByText(d[0]);
			if (!a) {
				throw "Could not find child node with text \"" + d[0] + "\"";
			}
			a.openPath(e, b, c);
		}
	}
};
_p.focus = function () {
};
_p.getFocused = function () {
	return this._focused;
};
_p._setTabIndex = function (a) {
	var b = this.getLabelElement();
	b && b.setAttribute("tabindex", a);
};
_p.toHtml = function () {
	for (var a = [], b = this.childNodes, c = b.length, d = 0; d < c; d++) {
		a[d] = b[d].toHtml();
	}
	b = this.getTree();
	a = "<div class=\"webfx-tree-children" + (!b.getShowLines() || b == this.parentNode && !b.getShowRootLines() ? "-nolines" : "") + "\" style=\"" + this.getLineStyle() + (this.getExpanded() && this.hasChildren() ? "" : "display:none;") + "\">" + a.join("") + "</div>";
	this.treeType.id && this.setId(this.treeType.id);
	return "<div class=\"webfx-tree-item\" id=\"" + this.id + "\"" + this.getEventHandlersHtml() + ">" + this.getRowHtml() + a + "</div>";
};
_p.getCheckboxHtml = function () {
	return "<input type=\"checkbox\" id=\"" + (webFXTreeHandler.checkboxPrefix + this.id) + "\" name=\"" + (this.treeType.name ? this.treeType.name : "checkbox-" + this.id) + "\" value=\"" + (this.treeType.value ? this.treeType.value : "") + "\" onclick=\"webFXTreeHandler.all['" + this.id + "'].doChecked(this,1,event);\" onmousedown=\"_stopEvent(event);\" " + (this.treeType.disabled ? "disabled=\"true\" " : "") + (this.treeType.checked ? "checked=\"true\" " : "") + "class=\"webfx-tree-checkbox\" />";
};
_p.getRadioHtml = function () {
	return "<input type=\"radio\" id=\"" + (webFXTreeHandler.radioPrefix + this.id) + "\" name=\"" + (this.treeType.name ? this.treeType.name : "radio-" + this.id) + "\"  value=\"" + (this.treeType.value ? this.treeType.value : "") + "\" onclick=\"webFXTreeHandler.all['" + this.id + "'].doChecked(this,0,event);\" onmousedown=\"_stopEvent(event);\" " + (this.treeType.disabled ? " disabled=\"true\" " : "") + (this.treeType.checked ? " checked=\"true\" " : "") + "class=\"webfx-tree-radio\" />";
};
_p.getRowHtml = function () {
	this.getTree();
	var a = "";
	if (this.treeType.type && "checkbox" == this.treeType.type.toLowerCase()) {
		a = this.getCheckboxHtml();
	} else {
		if (this.treeType.type && "radio" == this.treeType.type.toLowerCase()) {
			a = this.getRadioHtml();
		}
	}
	return "<div class=\"" + this.getRowClassName() + "\" style=\"padding-left:" + Math.max(0, (this.getDepth() - 1) * this.indentWidth) + "px\">" + this.getExpandIconHtml() + a + this.getIconHtml() + this.getLabelHtml() + "</div>";
};
_p.getRowClassName = function () {
	return "webfx-tree-row" + (this.isSelected() ? " selected" : "") + (this.action ? "" : " no-action");
};
_p.getLabelHtml = function () {
	var a = this.getToolTip(), b = this.getTarget() ? this.getTarget() : webFXTreeConfig.target;
	if (this.treeType.target && this.treeType.target != null) {
		b = this.treeType.target;
	}
	return "<a href=\"" + webFXTreeHandler.textToHtml(this._getHref()) + "\" class=\"webfx-tree-item-label\" tabindex=\"-1\"" + (a ? " title=\"" + webFXTreeHandler.textToHtml(a) + "\"" : "") + (b ? " target=\"" + b + "\"" : "") + " oncontextmenu=\"webFXTreeHandler.contextMenu(this,'" + this.getId() + "', event);return false;\">" + this.getHtml() + "</a>";
};
_p._getHref = function () {
	return typeof this.action == "string" ? this.action : "javascript:void(0);";
};
_p.getEventHandlersHtml = function () {
	return "";
};
_p.getIconHtml = function () {
	return "<img class=\"webfx-tree-icon\" src=\"" + this.getIconSrc() + "\">";
};
_p.getIconSrc = function () {
	throw new Error("getIconSrc called on Abstract Node");
};
_p.getExpandIconHtml = function () {
	return "<img class=\"webfx-tree-expand-icon\" src=\"" + this.getExpandIconSrc() + "\">";
};
_p.getExpandIconSrc = function () {
	var a = this.getTree(), b = !a.getShowLines() || a == this.parentNode && !a.getShowRootLines();
	if (this.hasChildren()) {
		var c = 0;
		if (a && a.getShowExpandIcons()) {
			c = this.getExpanded() ? 2 : 1;
		}
		if (a && !b) {
			c += this.isLastSibling() ? 4 : 8;
		}
		switch (c) {
		  case 1:
			return webFXTreeConfig.plusIcon;
		  case 2:
			return webFXTreeConfig.minusIcon;
		  case 4:
			return webFXTreeConfig.lIcon;
		  case 5:
			return webFXTreeConfig.lPlusIcon;
		  case 6:
			return webFXTreeConfig.lMinusIcon;
		  case 8:
			return webFXTreeConfig.tIcon;
		  case 9:
			return webFXTreeConfig.tPlusIcon;
		  case 10:
			return webFXTreeConfig.tMinusIcon;
		  default:
			return webFXTreeConfig.blankIcon;
		}
	} else {
		return a && b ? webFXTreeConfig.blankIcon : this.isLastSibling() ? webFXTreeConfig.lIcon : webFXTreeConfig.tIcon;
	}
};
_p.getLineStyle = function () {
	return "background-position:" + this.getLineStyle2() + ";";
};
_p.getLineStyle2 = function () {
	return (this.isLastSibling() ? "-100" : (this.getDepth() - 1) * this.indentWidth) + "px 0";
};
_p.getElement = function () {
	return document.getElementById(this.id);
};
_p.getRowElement = function () {
	var a = this.getElement();
	if (!a) {
		return null;
	}
	return a.firstChild;
};
_p.getExpandIconElement = function () {
	var a = this.getRowElement();
	if (!a) {
		return null;
	}
	return a.firstChild;
};
_p.getIconElement = function () {
	var a = this.getRowElement();
	if (!a) {
		return null;
	}
	return this.treeType.type == "normal" ? a.childNodes[1] : a.childNodes[2];
};
_p.getLabelElement = function () {
	var a = this.getRowElement();
	if (!a) {
		return null;
	}
	return a.lastChild;
};
_p.getChildrenElement = function () {
	var a = this.getElement();
	if (!a) {
		return null;
	}
	return a.lastChild;
};
_p.create = webFXTreeHandler.ie ? function () {
	var a = document.createElement("div");
	a.style.display = "none";
	document.body.appendChild(a);
	a.innerHTML = this.toHtml();
	var b = a.removeChild(a.firstChild);
	document.body.removeChild(a);
	return b;
} : function () {
	var a = document.createElement("div");
	a.innerHTML = this.toHtml();
	return a.removeChild(a.firstChild);
};
_p.setIcon = function (a) {
	this.icon = a;
	this.getCreated() && this.updateIcon();
};
_p.getIcon = function () {
	return this.icon;
};
_p.setOpenIcon = function (a) {
	this.openIcon = a;
	this.getCreated() && this.updateIcon();
};
_p.getOpenIcon = function () {
	return this.openIcon;
};
_p.setText = function (a) {
	this.setHtml(webFXTreeHandler.textToHtml(a));
};
_p.getText = function () {
	return webFXTreeHandler.htmlToText(this.getHtml());
};
_p.setHtml = function (a) {
	this.text = a;
	var b = this.getLabelElement();
	if (b) {
		b.innerHTML = a;
	}
};
_p.getHtml = function () {
	return this.text;
};
_p.setTarget = function (a) {
	this.target = a;
};
_p.getTarget = function () {
	return this.target;
};
_p.setToolTip = function (a) {
	this.toolTip = a;
	var b = this.getLabelElement();
	if (b) {
		b.title = a;
	}
};
_p.getToolTip = function () {
	return this.toolTip;
};
_p.setAction = function (a) {
	this.action = a;
	if (a = this.getLabelElement()) {
		a.href = this._getHref();
	}
	if (a = this.getRowElement()) {
		a.className = this.getRowClassName();
	}
};
_p.getAction = function () {
	return this.action;
};
_p.update = function () {
	var a = this.getTree();
	if (!a.suspendRedraw) {
		var b = this.getElement();
		if (b && b.parentNode) {
			var _checkedEle = document.getElementById(webFXTreeHandler.checkboxPrefix + b.id);
			if (!_checkedEle) {
				_checkedEle = document.getElementById(webFXTreeHandler.radioPrefix + b.id);
			}
			var c = this.create();
			b.parentNode.replaceChild(c, b);
			if (_checkedEle) {
				document.getElementById(_checkedEle.id).checked = _checkedEle.checked;
				if (webFXTreeConfig.cascadeChildCheck && "checkbox" == _checkedEle.type.toLowerCase()) {
					checkChildren(this, _checkedEle.checked);
				}
			}
			_checkedEle = null;
			(a = a.getSelected()) && a.getFocused() && a.focus();
		}
	}
};
_p.updateExpandIcon = function () {
	if (!this.getTree().suspendRedraw) {
		this.getExpandIconElement().src = this.getExpandIconSrc();
		this.getChildrenElement().style.backgroundPosition = this.getLineStyle2();
	}
};
_p.updateIcon = function () {
	if (!this.getTree().suspendRedraw) {
		this.getIconElement().src = this.getIconSrc();
	}
};
_p._callSuspended = function (a) {
	var b = this.getTree(), c = b.getSuspendRedraw();
	b.setSuspendRedraw(true);
	a.call(this);
	b.setSuspendRedraw(c);
};
_p._onmousedown = function (a) {
	if (/webfx-tree-expand-icon/.test((a.target || a.srcElement).className) && this.hasChildren()) {
		this.toggle();
		webFXTreeHandler.ie && window.setTimeout("WebFXTreeAbstractNode._onTimeoutFocus(\"" + this.id + "\")", 10);
		return false;
	}
	this.select();
	webFXTreeHandler.opera || (webFXTreeHandler.ie ? window.setTimeout("WebFXTreeAbstractNode._onTimeoutFocus(\"" + this.id + "\")", 10) : this.focus());
	if (a = this.getRowElement()) {
		a.className = this.getRowClassName();
	}
	return false;
};
_p._onclick = function () {
};
_p._ondblclick = function (a) {
	/webfx-tree-expand-icon/.test((a.target || a.srcElement).className) && this.hasChildren() || this.toggle();
};
_p._onfocus = function () {
	this.select();
	this._focused = true;
};
_p._onblur = function () {
	this._focused = false;
};
_p._onkeydown = function (a) {
	var b;
	b = true;
	switch (a.keyCode) {
	  case 39:
		if (a.altKey) {
			b = true;
			break;
		}
		if (this.hasChildren()) {
			this.getExpanded() ? this.getFirstChild().focus() : this.setExpanded(true);
		}
		b = false;
		break;
	  case 37:
		if (a.altKey) {
			b = true;
			break;
		}
		if (this.hasChildren() && this.getExpanded()) {
			this.setExpanded(false);
		} else {
			b = this.getParent();
			var c = this.getTree();
			if (b && (c.showRootNode || b != c)) {
				b.focus();
			}
		}
		b = false;
		break;
	  case 40:
		(b = this.getNextShownNode()) && b.focus();
		b = false;
		break;
	  case 38:
		(b = this.getPreviousShownNode()) && b.focus();
		b = false;
		break;
	}
	!b && a.preventDefault && a.preventDefault();
	return a.returnValue = b;
};
_p._onkeypress = function (a) {
	if (!a.altKey && a.keyCode >= 37 && a.keyCode <= 40) {
		a.preventDefault && a.preventDefault();
		return a.returnValue = false;
	}
};
_p.dispose = function () {
	if (!this.disposed) {
		for (var a = this.childNodes.length - 1; a >= 0; a--) {
			this.childNodes[a].dispose();
		}
		this.childNodes = this.parentNode = this.tree = null;
		this.disposed = true;
	}
};
_p.getLastShownDescendant = function () {
	if (!this.getExpanded() || !this.hasChildren()) {
		return this;
	}
	return this.getLastChild().getLastShownDescendant();
};
_p.getNextShownNode = function () {
	if (this.hasChildren() && this.getExpanded()) {
		return this.getFirstChild();
	} else {
		for (var a = this, b; a != null; ) {
			b = a.getNextSibling();
			if (b != null) {
				return b;
			}
			a = a.getParent();
		}
		return null;
	}
};
_p.getPreviousShownNode = function () {
	var a = this.getPreviousSibling();
	if (a != null) {
		return a.getLastShownDescendant();
	}
	a = this.getParent();
	var b = this.getTree();
	if (!b.showRootNode && a == b) {
		return null;
	}
	return a;
};
function WebFXTree(a, b) {
	var c = Object.extend(false, webFXTreeProp, a || {});
	this.treeType = c;
	WebFXTreeAbstractNode.call(this, c.text, c.action);
	if (c.icon) {
		this.icon = c.icon;
	}
	if (c.openIcon) {
		this.openIcon = c.openIcon;
	}
	if (b) {
		this.behavior = b;
	}
}
_p = WebFXTree.prototype = new WebFXTreeAbstractNode;
_p.indentWidth = 18;
_p.open = true;
_p._selectedItem = null;
_p._fireChange = true;
_p.rendered = false;
_p.suspendRedraw = false;
_p.showLines = true;
_p.showExpandIcons = true;
_p.showRootNode = true;
_p.showRootLines = true;
_p.getTree = function () {
	return this;
};
_p.getDepth = function () {
	return 0;
};
_p.getCreated = function () {
	return this.rendered;
};
_p.getExpanded = function () {
	return !this.showRootNode || WebFXTreeAbstractNode.prototype.getExpanded.call(this);
};
_p.setExpanded = function (a) {
	if (this.showRootNode) {
		WebFXTreeAbstractNode.prototype.setExpanded.call(this, a);
	} else {
		this.open = a;
	}
};
_p.getExpandIconHtml = function () {
	return "";
};
_p.getIconElement = function () {
	var a = this.getRowElement();
	if (!a) {
		return null;
	}
	return this.treeType.type == "normal" ? a.firstChild : a.childNodes[1];
};
_p.getExpandIconElement = function () {
	return null;
};
_p.updateExpandIcon = function () {
};
_p.getRowClassName = function () {
	return WebFXTreeAbstractNode.prototype.getRowClassName.call(this) + (this.showRootNode ? "" : " webfx-tree-hide-root");
};
_p.getIconSrc = function () {
	var a = this.getTree() ? this.getTree().getBehavior() : webFXTreeConfig.defaultBehavior;
	if ((a = a == "classic" && this.getExpanded() || a != "classic" && this.isSelected()) && this.openIcon) {
		return this.openIcon;
	}
	if (!a && this.icon) {
		return this.icon;
	}
	return a ? webFXTreeConfig.openRootIcon : webFXTreeConfig.rootIcon;
};
_p.getEventHandlersHtml = function () {
	return " onclick=\"return webFXTreeHandler.handleEvent(event)\" onmousedown=\"return webFXTreeHandler.handleEvent(event)\" ondblclick=\"return webFXTreeHandler.handleEvent(event)\" onkeydown=\"return webFXTreeHandler.handleEvent(event)\" onkeypress=\"return webFXTreeHandler.handleEvent(event)\"";
};
_p.setSelected = function (a) {
	this._selectedItem != a && a && a._setSelected(true);
};
_p._fireOnChange = function () {
	this._fireChange && typeof this.onchange == "function" && this.onchange();
};
_p.getSelected = function () {
	return this._selectedItem;
};
_p.getTreeNodeById = function (a) {
	return webFXTreeHandler.getNodeById(a);
};
_p.tabIndex = "";
_p.setTabIndex = function (a) {
	var b = this._selectedItem || (this.showRootNode ? this : this.firstChild);
	this.tabIndex = a;
	b && b._setTabIndex(a);
};
_p.getTabIndex = function () {
	return this.tabIndex;
};
_p.setBehavior = function (a) {
	this.behavior = a;
};
_p.getBehavior = function () {
	return this.behavior || webFXTreeConfig.defaultBehavior;
};
_p.setShowLines = function (a) {
	if (this.showLines != a) {
		this.showLines = a;
		this.rendered && this.update();
	}
};
_p.getShowLines = function () {
	return this.showLines;
};
_p.setShowRootLines = function (a) {
	if (this.showRootLines != a) {
		this.showRootLines = a;
		this.rendered && this.update();
	}
};
_p.getShowRootLines = function () {
	return this.showRootLines;
};
_p.setShowExpandIcons = function (a) {
	if (this.showExpandIcons != a) {
		this.showExpandIcons = a;
		this.rendered && this.getTree().update();
	}
};
_p.getShowExpandIcons = function () {
	return this.showExpandIcons;
};
_p.setShowRootNode = function (a) {
	if (this.showRootNode != a) {
		this.showRootNode = a;
		this.rendered && this.getTree().update();
	}
};
_p.getShowRoootNode = function () {
	return this.showRootNode;
};
_p.onchange = function () {
};
_p.create = function () {
	var a = WebFXTreeAbstractNode.prototype.create.call(this);
	this.setTabIndex(this.tabIndex);
	this.rendered = true;
	return a;
};
_p.write = function () {
	document.write(this.toHtml());
	this.setTabIndex(this.tabIndex);
	this.rendered = true;
};
_p.setSuspendRedraw = function (a) {
	this.suspendRedraw = a;
};
_p.getSuspendRedraw = function () {
	return this.suspendRedraw;
};
function WebFXTreeItem(a, b) {
	var c = Object.extend(false, webFXTreeProp, a || {});
	this.treeType = c;
	WebFXTreeAbstractNode.call(this, c.text, c.action);
	if (c.icon) {
		this.icon = c.icon;
	}
	if (c.openIcon) {
		this.openIcon = c.openIcon;
	}
	b && b.add(this);
}
_p = WebFXTreeItem.prototype = new WebFXTreeAbstractNode;
_p.tree = null;
_p.getDepth = function () {
	if (this.depth != null) {
		return this.depth;
	}
	if (this.parentNode) {
		var a = this.parentNode.getDepth();
		return this.depth = a != null ? a + 1 : null;
	}
	return null;
};
_p.getTree = function () {
	if (this.tree) {
		return this.tree;
	}
	if (this.parentNode) {
		return this.tree = this.parentNode.getTree();
	}
	return null;
};
_p.getCreated = function () {
	var a = this.getTree();
	return a && a.getCreated();
};
_p.getIconSrc = function () {
	var a = this.getTree() ? this.getTree().getBehavior() : webFXTreeConfig.defaultBehavior;
	if ((a = a == "classic" && this.getExpanded() || a != "classic" && this.isSelected()) && this.openIcon) {
		return this.openIcon;
	}
	if (!a && this.icon) {
		return this.icon;
	}
	if (this.hasChildren()) {
		return a ? webFXTreeConfig.openFolderIcon : webFXTreeConfig.folderIcon;
	}
	return webFXTreeConfig.fileIcon;
};
window.attachEvent && window.attachEvent("onunload", function () {
	for (var a in webFXTreeHandler.all) {
		webFXTreeHandler.all[a].dispose();
	}
});
function doCheckBoxClick(a, b) {
	var c = b.checked;
	if (webFXTreeConfig.cascadeChildCheck) {
		checkChildren(a, c);
		c ? checkParents(a) : unCheckParents(a);
	}
	webFXTreeConfig.checkEvent && typeof webFXTreeConfig.checkEvent == "function" && webFXTreeConfig.checkEvent(b, a);
}
function checkParents(a) {
	a = a.parentNode;
	if (a instanceof WebFXTreeAbstractNode) {
		if (webFXTreeConfig.allChildCheckToParent) {
			for (var b = 0; b < a.childNodes.length; b++) {
				var c = getCheckBoxObj(a.childNodes[b].id);
				if (c) {
					if (!c.checked || c.disabled) {
						return;
					}
				}
			}
		}
		if ((b = getCheckBoxObj(a.id)) && !b.disabled) {
			b.checked = true;
		}
		checkParents(a);
	}
}
function unCheckParents(a) {
	if (webFXTreeConfig.allChildUnCheckToParent) {
		a = a.parentNode;
		if (a instanceof WebFXTreeAbstractNode) {
			for (var b = 0; b < a.childNodes.length; b++) {
				if (c = getCheckBoxObj(a.childNodes[b].id)) {
					if (c != null && c.checked && !c.disabled) {
						return;
					}
				}
			}
			if ((c = getCheckBoxObj(a.id)) && !c.disabled) {
				c.checked = false;
			}
			unCheckParents(a);
		}
	} else {
		for (a = a.parentNode; a instanceof WebFXTreeAbstractNode; ) {
			var c = getCheckBoxObj(a.id);
			if (c) {
				if (c.checked && !c.disabled) {
					c.checked = false;
					a = a.parentNode;
				} else {
					return;
				}
			}
		}
	}
}
function checkChildren(a, b) {
	if (a.childNodes.length > 0) {
		a.expand();
		for (var c = 0; c < a.childNodes.length; c++) {
			var d = a.childNodes[c];
			if (d instanceof WebFXTreeAbstractNode) {
				checkChildren(d, b);
				d = getCheckBoxObj(d.id);
				if (!d) {
					continue;
				}
				if (!d.disabled) {
					d.checked = b;
				}
			}
			delete null;
		}
	}
}
function getCheckBoxObj(a) {
	if ((a = document.getElementById(webFXTreeHandler.checkboxPrefix + a)) && a.type == "checkbox") {
		return a;
	}
	return null;
}
function doRadioClick(a, b) {
	a.childNodes.length > 0 && a.expand();
	webFXTreeConfig.checkEvent && typeof webFXTreeConfig.checkEvent == "function" && webFXTreeConfig.checkEvent(b, a);
}
function _stopEvent(a) {
	a = a || window.event;
	if (a.stopPropagation) {
		a.stopPropagation();
	} else {
		a.cancelBubble = true;
	}
}
function WebFXLoadTree(a, b) {
	WebFXTree.call(this, a, b);
	this.src = a.src;
	this.loading = !a.src;
	this.loaded = !a.src;
	this.errorText = "";
	if (this.src) {
		this._loadingItem = WebFXLoadTree.createLoadingItem();
		this.add(this._loadingItem);
		this.getExpanded() && WebFXLoadTree.loadXmlDocument(this);
	}
}
WebFXLoadTree.createLoadingItem = function () {
	return new WebFXTreeItem({type:"normal", text:webFXTreeConfig.loadingText, icon:webFXTreeConfig.loadingIcon, openIcon:webFXTreeConfig.loadingIcon}, null);
};
_p = WebFXLoadTree.prototype = new WebFXTree;
_p.setExpanded = function (a) {
	WebFXTree.prototype.setExpanded.call(this, a);
	this.src && a && !this.loaded && !this.loading && WebFXLoadTree.loadXmlDocument(this);
};
function WebFXLoadTreeItem(a, b) {
	WebFXTreeItem.call(this, a, b);
	this.src = a.src;
	this.loading = !a.src;
	this.loaded = !a.src;
	this.errorText = "";
	if (this.src) {
		this._loadingItem = WebFXLoadTree.createLoadingItem();
		this.add(this._loadingItem);
		this.getExpanded() && WebFXLoadTree.loadXmlDocument(this);
	}
}
_p = WebFXLoadTreeItem.prototype = new WebFXTreeItem;
_p.setExpanded = function (a) {
	WebFXTreeItem.prototype.setExpanded.call(this, a);
	this.src && a && !this.loaded && !this.loading && WebFXLoadTree.loadXmlDocument(this);
};
WebFXLoadTree.prototype.load = _p.load = function () {
	this.setExpanded(true);
};
WebFXLoadTree.prototype.reload = _p.reload = function () {
	if (this.loaded) {
		var a = this.getTree(), b = this.getExpanded(), c = a.getSuspendRedraw();
		for (a.setSuspendRedraw(true); this.childNodes.length > 0; ) {
			this.remove(this.childNodes[this.childNodes.length - 1]);
		}
		this.loaded = false;
		this._loadingItem = WebFXLoadTree.createLoadingItem();
		this.add(this._loadingItem);
		b && this.setExpanded(true);
		a.setSuspendRedraw(c);
		this.update();
	} else {
		if (this.open && !this.loading) {
			WebFXLoadTree.loadXmlDocument(this);
		} else {
			!this.open && !this.loading && this.setExpanded(true);
		}
	}
};
WebFXLoadTree.prototype.setSrc = _p.setSrc = function (a) {
	if (a != this.src) {
		var b = this.getExpanded();
		this._callSuspended(function () {
			for (; this.childNodes.length > 0; ) {
				this.remove(this.childNodes[this.childNodes.length - 1]);
			}
		});
		this.update();
		this.loading = this.loaded = false;
		if (this._loadingItem) {
			this._loadingItem.dispose();
			this._loadingItem = null;
		}
		if (this.src = a) {
			this._loadingItem = WebFXLoadTree.createLoadingItem();
			this.add(this._loadingItem);
		}
		this.setExpanded(b);
	}
};
WebFXLoadTree.prototype.getSrc = _p.getSrc = function () {
	return this.src;
};
WebFXLoadTree.prototype.dispose = function () {
	WebFXTree.prototype.dispose.call(this);
	if (this._xmlHttp) {
		this._xmlHttp.dispose && this._xmlHttp.dispose();
		try {
			this._xmlHttp.onreadystatechange = null;
			this._xmlHttp.abort();
		}
		catch (a) {
		}
		this._xmlHttp = null;
	}
};
_p.dispose = function () {
	WebFXTreeItem.prototype.dispose.call(this);
	if (this._xmlHttp) {
		this._xmlHttp.dispose && this._xmlHttp.dispose();
		try {
			this._xmlHttp.onreadystatechange = null;
			this._xmlHttp.abort();
		}
		catch (a) {
		}
		this._xmlHttp = null;
	}
};
WebFXLoadTree.prototype.openPath = _p.openPath = function (a, b, c) {
	delete this._pathToOpen;
	this._selectPathOnLoad = b;
	this._focusPathOnLoad = c;
	if (a == "") {
		b && this.select();
		c && window.setTimeout("WebFXTreeAbstractNode._onTimeoutFocus(\"" + this.getId() + "\")", 10);
	} else {
		var d = a.split("/"), e = d.slice(1).join("/");
		if (a.charAt(0) == "/") {
			this.getTree().openPath(e, b, c);
		} else {
			this.setExpanded(true);
			if (this.loaded) {
				d = a.split("/");
				a = this.findChildByText(d[0]);
				if (!a) {
					throw "Could not find child node with text \"" + d[0] + "\"";
				}
				a.openPath(e, b, c);
			} else {
				this._pathToOpen = a;
			}
		}
	}
};
WebFXLoadTree._attrs = ["type", "name", "value", "text", "src", "action", "id", "target", "icon", "openIcon", "disabled", "checked"];
WebFXLoadTree.createItemFromElement = function (a) {
	var b = {}, c = a.attributes, d, e;
	e = c.length;
	for (d = 0; d < e; d++) {
		if (c[d] != null) {
			b[c[d].nodeName] = c[d].nodeValue;
		}
	}
	for (d = 0; d < WebFXLoadTree._attrs.length; d++) {
		e = WebFXLoadTree._attrs[d];
		if (c = a.getAttribute(e)) {
			b[e] = c;
		}
	}
	c = new WebFXLoadTreeItem(b, null);
	b.text && c.setText(b.text);
	if (b.target) {
		c.target = b.target;
	}
	b.id && c.setId(b.id);
	if (b.toolTip) {
		c.toolTip = b.toolTip;
	}
	b.expanded && c.setExpanded(b.expanded != "false");
	if (b.onload) {
		c.onload = new Function(b.onload);
	}
	if (b.onerror) {
		c.onerror = new Function(b.onerror);
	}
	c.attributes = b;
	a = a.childNodes;
	e = a.length;
	for (d = 0; d < e; d++) {
		a[d].tagName == "tree" && c.add(WebFXLoadTree.createItemFromElement(a[d]));
	}
	return c;
};
WebFXLoadTree.loadXmlDocument = function (a) {
	if (!(a.loading || a.loaded)) {
		a.loading = true;
		var b = a.getId();
		a._xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest : new window.ActiveXObject("Microsoft.XmlHttp");
		a._xmlHttp.open("GET", a.src, true);
		a._xmlHttp.onreadystatechange = new Function("WebFXLoadTree._onload(\"" + b + "\")");
		window.setTimeout("WebFXLoadTree._ontimeout(\"" + b + "\")", 10);
	}
};
WebFXLoadTree._onload = function (a) {
	a = webFXTreeHandler.all[a];
	if (a._xmlHttp.readyState == 4) {
		WebFXLoadTree.documentLoaded(a);
		webFXLoadTreeQueue.remove(a);
		a._xmlHttp.dispose && a._xmlHttp.dispose();
		a._xmlHttp = null;
	}
};
WebFXLoadTree._ontimeout = function (a) {
	webFXLoadTreeQueue.add(webFXTreeHandler.all[a]);
};
WebFXLoadTree.documentLoaded = function (a) {
	if (!a.loaded) {
		a.errorText = "";
		a.loaded = true;
		a.loading = false;
		var b = a.getTree(), c = b.getSuspendRedraw();
		b.setSuspendRedraw(true);
		var d = a._xmlHttp.responseXML;
		if (!d || d.parserError && d.parseError.errorCode != 0 || !d.documentElement) {
			a.errorText = !d || d.parseError.errorCode == 0 ? "\u52a0\u8f7d\u5931\u8d25\uff1a" + a.src + " (" + a._xmlHttp.status + ": " + a._xmlHttp.statusText + ")" : "\u52a0\u8f7d\u5931\u8d25\uff1a" + a.src + " (" + d.parseError.reason + ")";
		} else {
			var e = 0;
			d = d.documentElement.childNodes;
			for (var f = d.length, j = 0; j < f; j++) {
				if (d[j].tagName == "tree") {
					a.add(WebFXLoadTree.createItemFromElement(d[j]));
					e++;
				}
			}
		}
		if (a.errorText != "") {
			a._loadingItem.icon = webFXTreeConfig.errorIcon;
			a._loadingItem.text = a.errorText;
			a._loadingItem.action = WebFXLoadTree._reloadParent;
			a._loadingItem.toolTip = "\u70b9\u51fb\u91cd\u65b0\u52a0\u8f7d";
			b.setSuspendRedraw(c);
			a._loadingItem.update();
			typeof a.onerror == "function" && a.onerror();
		} else {
			a._loadingItem != null && a.remove(a._loadingItem);
			a._pathToOpen && a.openPath(a._pathToOpen, a._selectPathOnLoad, a._focusPathOnLoad);
			b.setSuspendRedraw(c);
			a.update();
			typeof a.onload == "function" && a.onload();
		}
	}
};
WebFXLoadTree._reloadParent = function () {
	this.getParent().reload();
};
var webFXLoadTreeQueue = {_nodes:[], _ie:/msie/i.test(navigator.userAgent), _opera:/opera/i.test(navigator.userAgent), add:function (a) {
	if (this._ie || this._opera) {
		this._nodes.push(a);
		this._nodes.length == 1 && this._send();
	} else {
		a._xmlHttp.send(null);
	}
}, remove:function (a) {
	if (this._ie || this._opera) {
		arrayHelper.remove(this._nodes, a);
		this._nodes.length > 0 && this._send();
	}
}, _send:function () {
	var a = this._nodes[0].getId();
	if (a = webFXTreeHandler.all[a]) {
		a._xmlHttp ? a._xmlHttp.send(null) : this.remove(a);
	}
}};
(function (a) {
	a.fn.TokenAutoComplete = function (b, c) {
		c = c ? c : {};
		var d = a.extend({url:b, width:null, tipText:"\u8bf7\u8f93\u5165\u5173\u952e\u8bcd...", noResultsText:"\u6ca1\u6709\u5339\u914d\u7684\u6570\u636e", searchingText:"\u6b63\u5728\u68c0\u7d22...", initData:null, minChars:1, tokenLimit:null, param:null, paramName:"tokenInput[]", queryParam:"q", cache:true, shownItems:10, formatItem:false, onSelect:false, onDelete:false, searchDelay:300, jsonContainer:null, method:"POST", contentType:"json", scrolled:false, mouseDownOnSelect:false, onResult:null}, c);
		c.classes = c.classes ? c.classes : {};
		d.classes = a.extend({tokenList:"gdk-token-ac", token:"gdk-token", selectedToken:"token-sel", tokenDelete:"token-del", inputToken:"gdk-token-input", autoTokenInput:"token-acinput", highlightedToken:"token-hover", dropdown:"gdk-token-dropdown", dropdownItemOdd:"ac-item-odd", dropdownItemEven:"ac-item-even", selectedDropdownItem:"ac-item-sel"}, c.classes);
		return this.each(function () {
			new a.TokenList(this, d);
		});
	};
	a.TokenList = function (b, c) {
		function d(o, q) {
			var z = a(o.target), u = null;
			if (z.is(q)) {
				u = z;
			} else {
				if (z.parent(q).length) {
					u = z.parent(q + ":first");
				}
			}
			return u;
		}
		function e(o) {
			var q = a("<li><input type=\"hidden\" name=\"" + c.paramName + "\" value=\"" + o.value + "\"/><p>" + o.label + "</p> </li>").addClass(c.classes.token).insertBefore(E);
			a("<span title=\"\u70b9\u51fb\u5220\u9664\">x</span>").addClass(c.classes.tokenDelete).appendTo(q).click(function () {
				r(a(this).parent());
				return false;
			});
			a.data(q.get(0), "tokeninput", o);
			return q;
		}
		function f(o) {
			o = a.data(o.get(0), "tokeninput");
			e(o);
			w.val("").focus();
			p();
			k++;
			A.val(k);
			if (c.tokenLimit != null && k >= c.tokenLimit) {
				w.hide();
				p();
			}
			c.onSelect && a.isFunction(c.onSelect) && c.onSelect(o);
		}
		function j(o) {
			o.addClass(c.classes.selectedToken);
			v = o.get(0);
			w.val("");
			p();
		}
		function n(o, q) {
			o.removeClass(c.classes.selectedToken);
			v = null;
			if (q == m.BEFORE) {
				E.insertBefore(o);
			} else {
				q == m.AFTER ? E.insertAfter(o) : E.appendTo(G);
			}
		}
		function r(o) {
			var q = a.data(o.get(0), "tokeninput");
			o.remove();
			v = null;
			k--;
			A.val(k);
			if (c.tokenLimit == null || c.tokenLimit != null && k < c.tokenLimit) {
				w.show().val("").focus();
			}
			c.onDelete && a.isFunction(c.onDelete) && c.onDelete(q);
		}
		function p() {
			C.hide().css({height:"auto"}).empty();
			D = null;
		}
		function x(o, q) {
			c.scrolled = false;
			if (q && q.length > 0) {
				C.empty();
				var z = a("<ul>").appendTo(C).mouseover(function (H) {
					g(d(H, "li"));
				}).click(function (H) {
					f(d(H, "li"));
					return false;
				}).hide(), u = null;
				for (var F in q) {
					u = null;
					if (q.hasOwnProperty(F)) {
						u = q[F];
						if (c.formatItem && a.isFunction(c.formatItem)) {
							u = a.extend({}, u, c.formatItem(u));
						}
						u = a("<li>" + u.label.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + o + ")(?![^<>]*>)(?![^&;]+;)", "gi"), "<b>$1</b>") + "</li>").appendTo(z);
						F % 2 ? u.addClass(c.classes.dropdownItemEven) : u.addClass(c.classes.dropdownItemOdd);
						F == 0 && g(u);
						a.data(u.get(0), "tokeninput", {label:q[F].label, value:q[F].value});
						u = null;
					}
				}
				u = null;
				c.scrolled = q.length > c.shownItems;
				C.show();
				z.slideDown("fast", function () {
					q.length > c.shownItems && C.find("li").length > 0 && C.css("height", C.find("li:first")[0].scrollHeight * c.shownItems + "px");
				});
			} else {
				C.html("<p>" + c.noResultsText + "</p>").show();
			}
		}
		function g(o) {
			if (o) {
				if (D) {
					a(D).removeClass(c.classes.selectedDropdownItem);
					D = null;
				}
				o.addClass(c.classes.selectedDropdownItem);
				D = o.get(0);
			}
		}
		function l(o) {
			var q = a.trim(w.val());
			if (q && q.length > 0) {
				v && n(a(v), m.AFTER);
				if (q.length >= c.minChars) {
					C.css({height:"auto"}).html("<p>" + c.searchingText + "</p>").show();
					if (o) {
						h(q);
					} else {
						if (B != null) {
							window.clearTimeout(B);
							B = null;
						}
						B = window.setTimeout(function () {
							h(q);
						}, c.searchDelay);
					}
				} else {
					p();
				}
			}
		}
		function h(o) {
			var q = s.get(o);
			if (q) {
				x(o, q, true);
			} else {
				c.url.indexOf("?");
				q = function (u) {
					if (!u || u == null) {
						return false;
					}
					if ("string" == typeof u) {
						u = eval("(" + u + ")");
					}
					if (a.isFunction(c.onResult)) {
						u = c.onResult.call(this, u);
					}
					if (c.cache) {
						s.add(o, c.jsonContainer ? u[c.jsonContainer] : u);
					}
					x(o, c.jsonContainer ? u[c.jsonContainer] : u, false);
				};
				var z = {};
				z[c.queryParam] = o;
				if (c.param) {
					z = a.extend(z, c.param);
				}
				c.method == "POST" ? a.post(c.url, z, q, c.contentType) : a.get(c.url, z, q, c.contentType);
				z = null;
			}
		}
		var m = {BEFORE:0, AFTER:1, END:2}, i = {BACKSPACE:8, TAB:9, RETURN:13, ESC:27, LEFT:37, UP:38, RIGHT:39, DOWN:40, COMMA:188}, k = 0, s = new a.TokenList.Cache, t = null, B, w = a("<input type=\"text\" autocomplete=\"off\">").css({outline:"none"}).addClass(c.classes.autoTokenInput).focus(function () {
			p();
			if (c.tokenLimit == null || c.tokenLimit != k) {
				C.css({height:"auto"}).html("<p>" + c.tipText + "</p>").show();
			}
		}).blur(function () {
			c.mouseDownOnSelect || p();
		}).keydown(function (o) {
			var q, z;
			switch (o.keyCode) {
			  case i.LEFT:
			  case i.RIGHT:
			  case i.UP:
			  case i.DOWN:
				if (a(this).val()) {
					var u = z = q = null;
					if (o.keyCode == i.DOWN || o.keyCode == i.RIGHT) {
						if ((q = a(D).next()) && q.length) {
							if (c.scrolled) {
								z = parseInt(q.prevAll("li:visible").length, 10);
								u = parseInt(q.nextAll("li:visible").length, 10);
								if ((z > Math.round(c.shownItems / 2) || u <= Math.round(c.shownItems / 2)) && typeof q.get(0) != "undefined") {
									C.get(0).scrollTop = parseInt(q.get(0).scrollHeight, 10) * (z - Math.round(c.shownItems / 2));
								}
							}
						} else {
							q = C.find("li:visible").first();
							if (c.scrolled) {
								C.get(0).scrollTop = 0;
							}
						}
					} else {
						if ((q = a(D).prev()) && q.length) {
							if (c.scrolled) {
								z = parseInt(q.prevAll("li:visible").length, 10);
								u = parseInt(q.nextAll("li:visible").length, 10);
								if ((u > Math.round(c.shownItems / 2) || z <= Math.round(c.shownItems / 2)) && typeof q.get(0) != "undefined") {
									C.get(0).scrollTop = parseInt(q.get(0).scrollHeight, 10) * (z - Math.round(c.shownItems / 2));
								}
							}
						} else {
							q = C.find("li:visible").last();
							if (c.scrolled) {
								C.get(0).scrollTop = C.get(0).scrollHeight - C.get(0).clientHeight;
							}
						}
					}
					q.length && g(q);
					u = z = q = null;
					return false;
				} else {
					q = E.prev();
					z = E.next();
					if (q.length && q.get(0) === v || z.length && z.get(0) === v) {
						o.keyCode == i.LEFT || o.keyCode == i.UP ? n(a(v), m.BEFORE) : n(a(v), m.AFTER);
					} else {
						if ((o.keyCode == i.LEFT || o.keyCode == i.UP) && q.length) {
							j(a(q.get(0)));
						} else {
							if ((o.keyCode == i.RIGHT || o.keyCode == i.DOWN) && z.length) {
								j(a(z.get(0)));
							}
						}
					}
				}
				break;
			  case i.BACKSPACE:
				q = E.prev();
				if (a(this).val().length) {
					if (a(this).val().length == 1) {
						p();
					} else {
						if (t != null) {
							window.clearTimeout(t);
							t = null;
						}
						t = window.setTimeout(function () {
							l(false);
						}, c.searchDelay);
					}
				} else {
					if (v) {
						r(a(v));
					} else {
						q.length && j(a(q.get(0)));
					}
					return false;
				}
				break;
			  case i.TAB:
			  case i.RETURN:
			  case i.COMMA:
				if (D) {
					f(a(D));
					return false;
				}
				break;
			  case i.ESC:
				p();
				return true;
			  default:
				break;
			}
		});
		a.browser.msie ? w.bind("propertychange", function () {
			if (t != null) {
				window.clearTimeout(t);
				t = null;
			}
			t = window.setTimeout(function () {
				l(false);
			}, c.searchDelay);
		}) : w.bind("input", function () {
			if (t != null) {
				window.clearTimeout(t);
				t = null;
			}
			t = window.setTimeout(function () {
				l(false);
			}, c.searchDelay);
		});
		if (!c.width || c.width == null) {
			c.width = a(b).width();
		}
		var A = a(b).hide().val(k).focus(function () {
			w.focus();
		}).blur(function () {
			w.blur();
		}), v = null, D = null, G = a("<ul />").addClass(c.classes.tokenList).insertBefore(A).width(parseInt(c.width, 10)).click(function (o) {
			if ((o = d(o, "li")) && o.get(0) != E.get(0)) {
				if (v == o.get(0)) {
					n(o, m.END);
				} else {
					v && n(a(v), m.END);
					j(o);
				}
				return false;
			} else {
				w.focus();
				v && n(a(v), m.END);
			}
		}).mouseover(function (o) {
			(o = d(o, "li")) && v !== this && o.addClass(c.classes.highlightedToken);
		}).mouseout(function (o) {
			(o = d(o, "li")) && v !== this && o.removeClass(c.classes.highlightedToken);
		}).mousedown(function (o) {
			if (d(o, "li")) {
				return false;
			}
		}), C = a("<div>").insertAfter(G).hide().addClass(c.classes.dropdown).width(a.browser.msie ? c.width : c.width - 2).mousedown(function (o) {
			c.mouseDownOnSelect = true;
			if (o.stopPropagation) {
				o.stopPropagation();
			} else {
				o.cancelBubble = true;
			}
			if (o.preventDefault) {
				o.preventDefault();
			} else {
				o.returnValue = false;
			}
		}).mouseup(function () {
			c.mouseDownOnSelect = false;
		}), E = a("<li />").addClass(c.classes.inputToken).appendTo(G).append(w);
		(function () {
			if ((li_data = c.initData) && li_data.length) {
				for (var o = 0; o < li_data.length; o++) {
					var q = a("<li><input type=\"hidden\" name=\"" + c.paramName + "\" value=\"" + li_data[o].value + "\"/><p>" + li_data[o].label + "</p> </li>").addClass(c.classes.token).insertBefore(E);
					a("<span title=\"\u70b9\u51fb\u5220\u9664\">x</span>").addClass(c.classes.tokenDelete).appendTo(q).click(function () {
						r(a(this).parent());
						return false;
					});
					a.data(q.get(0), "tokeninput", {label:li_data[o].label, value:li_data[o].value});
					k++;
				}
				w.val("");
				p();
				if (c.tokenLimit != null && k >= c.tokenLimit) {
					w.hide();
					p();
				}
				A.val(k);
			}
		})();
		a(document.body).bind("mousedown", function () {
			p();
		});
	};
	a.TokenList.Cache = function (b) {
		var c = a.extend({max_size:50}, b), d = {}, e = 0;
		this.add = function (f, j) {
			if (e > c.max_size) {
				d = {};
				e = 0;
			}
			d[f] || e++;
			d[f] = j;
		};
		this.get = function (f) {
			return d[f];
		};
	};
})(jQuery);
(function (a) {
	a.fn.NumericStepper = function (b) {
		var c = {version:"1.0.0", id:null, mininum:null, maxinum:null, stepsize:1, value:0, width:50, nsBtnLen:20, stTimes:500, itTimes:50}, d = function (h) {
			h = h.toString().split(".");
			h = h.length == 2 ? h[1].length : 0;
			if (h.length >= 20) {
				h = 20;
			}
			return h;
		}, e = null, f = null, j = a.extend({}, c, b), n = a(this).attr("id") || a(this).attr("name");
		a(this).attr("id", n);
		j.id = n;
		a(this).css({width:j.width + "px", height:"22px"});
		a("#GF-NS-" + j.id).css({width:j.width + j.nsBtnLen + "px"}).show();
		var r = 0;
		r = Math.max(d(j.mininum), d(j.maxinum), d(j.stepsize));
		a(this).val(Number(j.value).toFixed(r));
		a(this).bind("blur", function () {
			var h = a(this).val();
			j.maxinum !== null && Number(h) > Number(j.maxinum) && a(this).val(j.maxinum);
			j.mininum !== null && Number(h) < Number(j.mininum) && a(this).val(j.mininum);
		}).bind("keydown", function (h) {
			h = h.which ? h.which : h.keyCode;
			if (h == 38 || h == 33) {
				g(this, j);
			}
			if (h == 40 || h == 34) {
				l(this, j);
			}
		}).bind("keyup", function (h) {
			h = h.which ? h.which : h.keyCode;
			if (h == 38 || h == 33) {
				p();
			}
			if (h == 40 || h == 34) {
				p();
			}
		});
		a("#NSUp-" + j.id).bind("mousedown", function () {
			if (a(this).attr("disabled") == "disabled") {
				return false;
			}
			a(this).addClass("mp-dn");
			x(j, "UP");
		}).bind("mouseup", function () {
			if (a(this).attr("disabled") == "disabled") {
				return false;
			}
			a(this).removeClass("mp-dn");
			p();
		}).hover(function () {
			if (a(this).attr("disabled") == "disabled") {
				return false;
			}
			a(this).addClass("mp-hover");
		}, function () {
			if (a(this).attr("disabled") == "disabled") {
				return false;
			}
			a(this).removeClass("mp-hover");
		});
		a("#NSDown-" + j.id).bind("mousedown", function () {
			if (a(this).attr("disabled") == "disabled") {
				return false;
			}
			a(this).addClass("md-dn");
			x(j, "DOWN");
		}).bind("mouseup", function () {
			if (a(this).attr("disabled") == "disabled") {
				return false;
			}
			a(this).removeClass("md-dn");
			p();
		}).hover(function () {
			if (a(this).attr("disabled") == "disabled") {
				return false;
			}
			a(this).addClass("md-hover");
		}, function () {
			if (a(this).attr("disabled") == "disabled") {
				return false;
			}
			a(this).removeClass("md-hover");
		});
		var p = function () {
			f && window.clearTimeout(f);
			e && window.clearInterval(e);
			e = f = null;
			j.itTimes = c.itTimes;
			a("#" + n).focus();
		}, x = function (h, m) {
			var i = a("#" + h.id)[0];
			if (i.value === "") {
				i.value = h.mininum ? h.mininum : 0;
			}
			switch (m) {
			  case "UP":
				g(i, h);
				f = window.setTimeout(function () {
					e = window.setInterval(function () {
						g(i, h);
					}, j.itTimes);
				}, j.stTimes);
				break;
			  case "DOWN":
				l(i, h);
				f = window.setTimeout(function () {
					e = window.setInterval(function () {
						l(i, h);
					}, j.itTimes);
				}, j.stTimes);
				break;
			}
		}, g = function (h, m) {
			if (m.maxinum !== null && Number(h.value) >= Number(m.maxinum)) {
				p();
				return false;
			}
			h.value = (Number(h.value) + Number(m.stepsize)).toFixed(r);
			if (j.itTimes > 10) {
				j.itTimes -= 10;
			}
		}, l = function (h, m) {
			if (m.mininum !== null && Number(h.value) <= Number(m.mininum)) {
				p();
				return false;
			}
			h.value = (Number(h.value) - Number(m.stepsize)).toFixed(r);
			if (j.itTimes > 10) {
				j.itTimes -= 10;
			}
		};
	};
})(jQuery);
var ToolButton = {};
ToolButton.enable = function (a) {
	$("#" + a).removeAttr("disabled").removeClass("tbtn-disabled");
};
ToolButton.disable = function (a) {
	$("#" + a).attr("disabled", true).addClass("tbtn-disabled");
};
ToolButton.isDisabled = function (a) {
	return $(a).attr("disabled") == "true" || $(a).attr("disabled") === true;
};
ToolButton.click = function (a) {
	$("#" + a).click();
};
ToolButton.init = function () {
	$(".GF-toolbtn").hover(function () {
		if (ToolButton.isDisabled(this)) {
			return false;
		}
		$(this).addClass("tbtn-hover");
	}, function () {
		if (ToolButton.isDisabled(this)) {
			return false;
		}
		$(this).removeClass("tbtn-hover");
	}).click(function () {
		if (ToolButton.isDisabled(this)) {
			return false;
		}
		eval($(this).attr("click"));
	});
};
$(function () {
	ToolButton.init();
});
function tblMouseXY(a) {
	a = a || window.event;
	var b = null, c = null;
	b = a.pageX || a.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft);
	c = a.pageY || a.clientY + (document.documentElement.scrollTop || document.body.scrollTop);
	return {x:b, y:c};
}
function tblClearEvent(a) {
	if (a.stopPropagation) {
		a.stopPropagation();
	} else {
		a.cancelBubble = true;
	}
	if (a.preventDefault) {
		a.preventDefault();
	} else {
		a.returnValue = false;
	}
}
function TableLayout(a) {
	this.type = a.type;
	this.region = a.region;
	this.resizeBar = a.resizeBar;
	this.resizeArea = a.resizeArea;
	this.toggle = typeof a.toggle == "boolean" ? a.toggle : false;
	this.visible = typeof a.visible == "boolean" ? a.visible : true;
	this.minWidth = a.minWidth || 50;
	this.maxWidth = a.maxWidth;
	this.minHeight = a.minHeight || 50;
	this.maxHeight = a.maxHeight;
	this.resizeMove = false;
	this.resizeBarId = "_resizeBar_" + this.resizeBar;
	this.startY = this.startX = this.offsetT = this.offsetL = 0;
	this.resizeAreaObj = this.resizeBarObj = null;
	if (this.type != "V" && this.type != "H") {
		alert("TableLayout\u7684type\u5b9a\u4e49\u9519\u8bef\uff0c\u5176\u503c\u662f\uff1aV | H \uff01");
	} else {
		if (this.region != "left" && this.region != "right" && this.region != "top" && this.region != "bottom") {
			alert("TableLayout\u7684region\u5b9a\u4e49\u9519\u8bef\uff0c\u5176\u503c\u662f\uff1aleft | right | top | bottom \uff01");
		} else {
			if (!this.resizeBar || !document.getElementById(this.resizeBar)) {
				alert("TableLayout\u65e0\u6cd5\u53d1\u73b0\u5b9a\u4e49\u7684 resizeBar !");
			} else {
				if (!this.resizeArea || !document.getElementById(this.resizeArea)) {
					alert("TableLayout\u65e0\u6cd5\u53d1\u73b0\u5b9a\u4e49\u7684 resizeArea \u533a\u57df !");
				} else {
					var b = this;
					var c = a = null;
					a = b.type == "V" ? "v-tbl-rb" : b.type == "H" ? "h-tbl-rb" : "";
					c = b.type == "V" ? "width" : b.type == "H" ? "height" : "";
					$("#" + b.resizeBar).removeAttr(c).attr("align", "center").attr("valign", "middle").addClass(a).html("<div id=\"" + b.resizeBar + "-tib\"></div>").bind("mousedown", {tbl:b}, b.resizeStart);
					if (b.type == "V") {
						a = $("#" + b.resizeArea);
						a.removeAttr("width").css({width:a.width() + "px"});
						$("#" + b.resizeBar + "-tib").addClass("tbl-vdiv");
					} else {
						a = $("#" + b.resizeArea);
						a.removeAttr("height").css({height:a.height() + "px"});
						$("#" + b.resizeBar + "-tib").addClass("tbl-hdiv");
					}
					if (!b.visible) {
						if (b.type == "V") {
							$("#" + b.resizeArea).hide();
						} else {
							b.type == "H" && $("#" + b.resizeArea).parent("tr").hide();
						}
					}
					b.toggle && $("#" + b.resizeBar + "-tib").addClass(b.getToggleClass()).bind("click", function () {
						var d = "";
						if (b.type == "V") {
							d = "tbl-vdiv";
							$("#" + b.resizeArea).toggle();
						} else {
							if (b.type == "H") {
								d = "tbl-hdiv";
								$("#" + b.resizeArea).parent("tr").toggle();
							}
						}
						this.className = d + " " + b.getToggleClass();
					}).mousedown(function (d) {
						tblClearEvent(d);
						return false;
					});
					a = c = null;
				}
			}
		}
	}
}
TableLayout.prototype.getToggleClass = function () {
	var a = "";
	if (this.type == "V") {
		a = $("#" + this.resizeArea).css("display") == "none" ? this.region == "left" ? "v-tor" : "v-tol" : this.region == "left" ? "v-tol" : "v-tor";
	} else {
		if (this.type == "H") {
			a = $("#" + this.resizeArea).parent("tr").css("display") == "none" ? this.region == "top" ? "h-tod" : "h-tou" : this.region == "top" ? "h-tou" : "h-tod";
		}
	}
	return a;
};
TableLayout.prototype.resizeStart = function (a) {
	var b = null, c = null, d = null, e = null, f = null, j = null, n = null, r = null;
	try {
		b = a.data.tbl;
		if (!($("#" + b.resizeArea).css("display") == "none" || $("#" + b.resizeArea).parent("tr").css("display") == "none")) {
			c = b.resizeBarId;
			document.getElementById("tbl_resize_layer") || $(document.body).append("<div id='tbl_resize_layer' class='tbl-resize-layer'></div>");
			$("#tbl_resize_layer").show();
			if (!document.getElementById(c)) {
				r = b.type == "V" ? "tbl-rb-v" : b.type == "H" ? "tbl-rb-h" : "";
				$(document.body).append("<div id='" + c + "' class='tbl-resize-bar " + r + "'></div>");
			}
			d = $("#" + b.resizeBar);
			e = d.offset().left;
			f = d.offset().top;
			j = d.width();
			n = d.height();
			b.offsetL = e;
			b.offsetT = f;
			b.startX = tblMouseXY(a).x;
			b.startY = tblMouseXY(a).y;
			$("#" + c).css({left:e + "px", top:f + "px", width:j + "px", height:n + "px"}).show();
			$(document).bind("mousemove", {tbl:b}, b.resizeDoing);
			$(document).bind("mouseup", {tbl:b}, b.resizeEnd);
			tblClearEvent(a);
			return false;
		}
	}
	catch (p) {
	}
	finally {
	}
};
TableLayout.prototype.resizeDoing = function (a) {
	var b = null, c = null, d = null, e = null, f = null, j = null, n = null, r = null;
	try {
		b = a.data.tbl;
		if (!b.resizeMove) {
			b.resizeMove = true;
		}
		c = tblMouseXY(a);
		if (b.type == "H") {
			r = b.offsetT + c.y - b.startY;
			if (b.minHeight || b.maxHeight) {
				f = b.region == "top" ? r - b.offsetT : b.offsetT - r;
				j = $("#" + b.resizeArea).height() + f;
				if (j <= b.minHeight || j >= b.maxHeight) {
					return;
				}
			}
			$("#" + b.resizeBarId).css({top:r + "px"});
		} else {
			if (b.type == "V") {
				n = b.offsetL + c.x - b.startX;
				if (b.minWidth || b.maxWidth) {
					d = b.region == "left" ? n - b.offsetL : b.offsetL - n;
					e = $("#" + b.resizeArea).width() + d;
					if (e <= b.minWidth || e >= b.maxWidth) {
						return;
					}
				}
				$("#" + b.resizeBarId).css({left:n + "px"});
			}
		}
	}
	catch (p) {
	}
	finally {
		return false;
	}
};
TableLayout.prototype.resizeEnd = function (a) {
	var b = null, c = null, d = null, e = null, f = null, j = null, n = null, r = null, p = null;
	try {
		b = a.data.tbl;
		if (b.resizeMove) {
			c = $("#" + b.resizeBarId);
			d = $("#" + b.resizeArea);
			if (b.type == "H") {
				e = parseInt(c.css("top"));
				f = b.region == "top" ? e - b.offsetT : b.offsetT - e;
				j = parseInt(d.height()) + f;
				d.css({height:j + "px"});
			} else {
				if (b.type == "V") {
					n = parseInt(c.css("left"));
					r = b.region == "left" ? n - b.offsetL : b.offsetL - n;
					p = parseInt(d.width()) + r;
					d.css({width:p + "px"});
				}
			}
		}
	}
	catch (x) {
	}
	finally {
		b.resizeMove = false;
		$("#" + b.resizeBarId).hide();
		$("#tbl_resize_layer").hide();
		$(document).unbind("mousemove", b.resizeStart);
		$(document).unbind("mouseup", b.resizeEnd);
	}
};
(function (a) {
	function b(d) {
		d = d || window.event;
		var e = null, f = null;
		e = d.pageX || d.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft);
		f = d.pageY || d.clientY + (document.documentElement.scrollTop || document.body.scrollTop);
		return {x:e, y:f};
	}
	function c(d) {
		if (d.stopPropagation) {
			d.stopPropagation();
		} else {
			d.cancelBubble = true;
		}
		if (d.preventDefault) {
			d.preventDefault();
		} else {
			d.returnValue = false;
		}
	}
	a.fn.iTableLayout = function (d) {
		var e = {vW:5, hH:5, minWidth:10, minHeight:10, region:{NORTH:"north", SOUTH:"south", EAST:"east", WEST:"west"}, classes:{tbl:"gdk-tablelayout", tbl_vbar:"tbl-vhd", tbl_vbar_div:"tbl-vhb", tbl_vbar_img:"vhd-img", tbl_hbar:"tbl-hhd", tbl_hbar_div:"tbl-hhb", tbl_hbar_img:"hhd-img", tbl_area:"tbl-area", tbl_resize_bar:"tbl-resize-bar", tbl_cover_mask:"tbl-cover-mask"}};
		if (!d || d.length <= 0) {
			return false;
		}
		return this.each(function () {
			new a.iRenderLayout(this, d, e);
		});
	};
	a.iRenderLayout = function (d, e, f) {
		function j(i) {
			var k = a("#" + i.handler);
			k.removeAttr("width").attr({align:"center", valign:"middle"}).addClass(f.classes.tbl_vbar).html("<div id=\"handler-" + i.handler + "\" class=\"" + f.classes.tbl_vbar_div + " " + f.classes.tbl_vbar_img + "\"><i>&nbsp;</i></div>");
			k.prev("td").addClass(f.classes.tbl_area);
			k.next("td").addClass(f.classes.tbl_area);
			i.width && a("#" + i.resizeArea).removeAttr("width").width(i.width);
			if (i.resizable === false || i.resizable == "false") {
				k.css("cursor", "default");
				a("#handler-" + i.handler).removeClass(f.classes.tbl_vbar_img);
				k = null;
				return false;
			}
			k.mousedown(function (s) {
				g(this, i, s);
			});
			k = null;
		}
		function n(i) {
			var k = a("#" + i.handler);
			k.removeAttr("height").attr("align", "center").addClass(f.classes.tbl_hbar).html("<div id=\"handler-" + i.handler + "\" class=\"" + f.classes.tbl_hbar_div + " " + f.classes.tbl_hbar_img + "\"><i>&nbsp;</i></div>");
			k.parent("tr").prev("tr").find("td").first().addClass(f.classes.tbl_area);
			k.parent("tr").next("tr").find("td").first().addClass(f.classes.tbl_area);
			i.height && a("#" + i.resizeArea).removeAttr("height").height(i.height);
			if (i.resizable === false || i.resizable == "false") {
				k.css("cursor", "default");
				a("#handler-" + i.handler).removeClass(f.classes.tbl_hbar_img);
				k = null;
				return false;
			}
			k.mousedown(function (s) {
				g(this, i, s);
			});
			k = null;
		}
		function r(i) {
			return i.toLowerCase() == f.region.EAST || i.toLowerCase() == f.region.WEST;
		}
		function p(i) {
			return i.toLowerCase() == f.region.NORTH || i.toLowerCase() == f.region.SOUTH;
		}
		function x() {
			if (!document.getElementById("_TblCoverMask_")) {
				var i = a("<div id=\"_TblCoverMask_\" class=\"" + f.classes.tbl_cover_mask + "\">&nbsp;</div>");
				a(document.body).append(i);
				i.hide().css({position:"absolute", left:"0px", top:"0px", width:"100%", height:"100%", opacity:0.1});
			}
			return document.getElementById("_TblCoverMask_");
		}
		function g(i, k, s) {
			c(s);
			var t = a("#" + k.resizeArea);
			if (t.css("display") == "none" || t.parent("tr").css("display") == "none") {
				return false;
			}
			var B = x();
			a(B).show();
			B = a;
			var w;
			if (!document.getElementById("_TblResizeBar_")) {
				w = a("<div id=\"_TblResizeBar_\" class=\"" + f.classes.tbl_resize_bar + "\">&nbsp;</div>");
				a(document.body).append(w);
				w.hide().css({position:"absolute"});
			}
			w = document.getElementById("_TblResizeBar_");
			B = B(w);
			w = a(i).offset();
			var A = null, v = null;
			v = A = null;
			if (r(k.region)) {
				A = f.vW;
				v = a(i).height();
				a(document.body).css("cursor", "col-resize");
			} else {
				if (p(k.region)) {
					A = a(i).width();
					v = f.hH;
					a(document.body).css("cursor", "row-resize");
				}
			}
			B.css({left:w.left + "px", top:w.top + "px", width:A + "px", height:v + "px"}).show();
			document.all && B[0].setCapture();
			A = t.width();
			v = t.height();
			i = {resizeBar:B, opt:k, ow:A, oh:v, ol:w.left, ot:w.top, startX:b(s).x, startY:b(s).y};
			a(document).bind("mousemove", {tblData:i}, l);
			a(document).bind("mouseup", {tblData:i}, h);
		}
		function l(i) {
			c(i);
			m || (m = true);
			var k = i.data.tblData;
			if (r(k.opt.region)) {
				i = b(i).x - k.startX;
				if (k.opt.maxWidth || k.opt.minWidth) {
					var s = null;
					s = k.opt.region.toLowerCase() == "west" ? k.ow + i : k.ow - i;
					if (s >= k.opt.maxWidth || s <= k.opt.minWidth) {
						return false;
					}
				}
				k.resizeBar.css({left:i + k.ol + "px"});
			} else {
				if (p(k.opt.region)) {
					i = b(i).y - k.startY;
					if (k.opt.maxHeight || k.opt.minHeight) {
						s = null;
						s = k.opt.region.toLowerCase() == "north" ? k.oh + i : k.oh - i;
						if (s >= k.opt.maxHeight || s <= k.opt.minHeight) {
							return false;
						}
					}
					k.resizeBar.css({top:i + k.ot + "px"});
				}
			}
			return false;
		}
		function h(i) {
			a(document).unbind("mousemove", l);
			a(document).unbind("mouseup", h);
			a(document.body).css("cursor", "default");
			var k = i.data.tblData, s = x(), t = k.resizeBar;
			a(s).hide();
			document.all && t[0].releaseCapture();
			s = null;
			if (m) {
				s = a("#" + k.opt.resizeArea);
				cl = parseInt(t.css("left"), 10) - k.ol;
				ct = parseInt(t.css("top"), 10) - k.ot;
				if (r(k.opt.region)) {
					k.opt.region.toLowerCase() == f.region.WEST ? s.width(k.ow + cl) : s.width(k.ow - cl);
				} else {
					if (p(k.opt.region)) {
						k.opt.region.toLowerCase() == f.region.NORTH ? s.height(k.oh + ct) : s.height(k.oh - ct);
					}
				}
			}
			m = false;
			t.hide();
			ct = cl = i.data.tblData = null;
			return false;
		}
		a(d).hide().addClass(f.classes.tbl).attr({cellspacing:0, cellpadding:0});
		(function () {
			for (var i = null, k = 0; k < e.length; k++) {
				i = e[k];
				if (r(i.region)) {
					j(i);
				} else {
					p(i.region) && n(i);
				}
			}
			a(d).show();
		})();
		var m = false;
	};
})(jQuery);
var iPopupMenu = function (a, b, c, d) {
	if (!a) {
		return false;
	}
	c || (c = this);
	var e = this, f = function () {
	}, j = this.MUI("UL", null, false, e.classes.ul);
	if (d) {
		j.style.width = parseInt(d, 10) + "px";
	}
	j.onclick = j.onmousedown = function (p) {
		e.cancelEvent(p);
	};
	j.oncontextmenu = function (p) {
		e.cancelEvent(p);
		return false;
	};
	e.UI = j;
	for (d = 0; d < a.length; d++) {
		var n = a[d], r = this.MUI("LI", j, n.id ? n.id : false, n.disabled ? e.classes.liDis : e.classes.li);
		if (n.line) {
			r.className = e.classes.liLine;
		} else {
			if (n.disabled) {
				r.disabled = true;
			}
			r.onmouseover = function () {
				for (var p = this.parentNode.getElementsByTagName("LI"), x = 0; x < p.length; x++) {
					var g = p[x];
					if (g != this) {
						g.className == e.classes.liOpen && (g.className = e.classes.li);
						g.subMenu && g.subMenu.hide();
					}
				}
			};
			if (n.ele) {
				r.innerHTML = document.getElementById(n.ele).innerHTML;
			} else {
				if (n.radio != undefined) {
					r.setAttribute("radio", n.radio);
					n.icoCss = this.classes.rdo;
					if (n.selected == true && !this.hasOnlyRadio) {
						n.icoCss = this.classes.rdoSel;
						this.hasOnlyRadio = true;
					}
				}
				if (n.multi != undefined) {
					r.setAttribute("multi", n.multi);
					n.icoCss = this.classes.multi;
					if (n.selected == true) {
						n.icoCss = this.classes.multiSel;
						r.selected = true;
					} else {
						r.selected = false;
					}
				}
				this.MUI("SPAN", this.MUI("A", r, false, n.sub ? e.classes.liASub : e.classes.liA), false, e.classes.liSpan, n.text, n.ico, n.icoCss);
				if (n.sub) {
					new iPopupMenu(n.sub, r, c, n.width ? n.width : false);
				} else {
					if (typeof n.click != "function") {
						n.click = this.click;
					}
					r.onclick = function (p) {
						return function (x) {
							if (this.disabled) {
								e.cancelEvent(x);
								return false;
							}
							c.hide();
							if (p.radio) {
								x = this.parentNode.getElementsByTagName("LI");
								for (var g = this.getAttribute("radio"), l = 0; l < x.length; l++) {
									var h = x[l];
									if (g == h.getAttribute("radio")) {
										var m = h.getElementsByTagName("EM");
										if (m != null) {
											m[0].className = h == this ? e.classes.rdoSel : e.classes.rdo;
										}
									}
								}
								p.selected = this.selected = true;
							} else {
								if (p.multi) {
									h = this.selected == true;
									m = this.getElementsByTagName("EM");
									if (m != null) {
										m[0].className = !h ? e.classes.multiSel : e.classes.multi;
									}
									p.selected = this.selected = !h;
								}
							}
							p.click(p, e.targetEle);
						};
					}(n);
				}
			}
		}
	}
	if (b) {
		if (typeof b.onmouseover == "function") {
			f = b.onmouseover;
		}
		b.onmouseover = function () {
			if (b.disabled == true) {
				return false;
			}
			var p = this;
			p.showTimer && window.clearTimeout(p.showTimer);
			p.showTimer = window.setTimeout(function () {
				f.call(p);
				p.className = e.classes.liOpen;
				var x = e.absPos(p);
				e.show(x.x + p.offsetWidth - 2, x.y + 5, p);
			}, 200);
		};
		b.onmouseout = function () {
			window.clearTimeout(this.showTimer);
			this.hideTimer = setTimeout(function () {
				e.hide();
			}, 50);
		};
		b.getElementsByTagName("SPAN")[0].onmouseover = this.UI.onmouseover = function () {
			clearTimeout(b.hideTimer);
		};
		b.subMenu = this;
	}
};
iPopupMenu.prototype = {absPos:function (a) {
	var b = y = 0;
	do {
		b += a.offsetLeft;
		y += a.offsetTop;
	} while (a = a.offsetParent);
	return {x:b, y:y};
}, popUp:function (a, b) {
	this.show(a, b);
}, MUI:function (a, b, c, d, e, f, j) {
	var n = document;
	b = (b || n.body).appendChild(n.createElement(a));
	if (c) {
		b.id = c;
	}
	if (d) {
		b.className = d;
	}
	if (e) {
		b.innerHTML = "<em class=\"imenu-ico\"></em>" + e;
	}
	a.toUpperCase() == "A" && b.setAttribute("href", "javascript:void(0)");
	if (f) {
		b.getElementsByTagName("EM")[0].style.backgroundImage = "url(" + f + ")";
	}
	if (j) {
		b.getElementsByTagName("EM")[0].className = j;
	}
	return b;
}, show:function (a, b, c) {
	this.hide();
	var d = this.UI.offsetWidth, e = this.UI.offsetHeight, f = document.body, j = f.clientWidth + f.scrollLeft;
	f = f.clientHeight + f.scrollTop;
	if (e + b + 5 > f) {
		b = f - e - 1;
	} else {
		if (c) {
			b -= 5;
		}
	}
	if (d + a + 5 > j) {
		a -= d;
		if (c) {
			a -= c.offsetWidth - 5;
		}
	} else {
		if (c) {
			a += 0;
		}
	}
	c = this.UI.style;
	c.visibility != "visible" && (c.visibility = "visible");
	c.top = b + "px";
	c.left = a + "px";
}, hide:function () {
	var a = this.UI.style, b = this.UI.getElementsByTagName("LI");
	a.visibility != "hidden" && (a.visibility = "hidden");
	for (var c = 0; c < b.length; c++) {
		a = b[c];
		a.className == this.classes.liOpen && (a.className = this.classes.li);
		a.subMenu && a.subMenu.hide();
	}
}, click:function () {
}, setTargetEle:function (a) {
	this.targetEle = a;
	var b = this.UI.getElementsByTagName("LI");
	if (!b || b.length <= 0) {
		return false;
	}
	for (var c, d = 0; d < b.length; d++) {
		(c = b[d]) && c.subMenu && c.subMenu.setTargetEle(a);
	}
}, cancelEvent:function (a) {
	a = a || window.event;
	if (a.stopPropagation) {
		a.stopPropagation();
	} else {
		a.cancelBubble = true;
	}
}, classes:{ul:"gdk-imenu", li:"imenu-li", liDis:"imenu-li imenu-dis", liOpen:"imenu-li imenu-open", liLine:"imenu-li splitLine", liA:"imenu-a", liASub:"imenu-a toSub", liSpan:"imenu-text", liIco:"imenu-ico", rdo:"imenu-ico", rdoSel:"imenu-ico ico-rdo-sel", multi:"imenu-ico", multiSel:"imenu-ico ico-cb-sel"}};
var iMenu = {menuList:Array(), menuInit:false, absEvent:function (a) {
	a = a || window.event;
	var b = {};
	b.x = a.pageX || a.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft);
	b.y = a.pageY || a.clientY + (document.documentElement.scrollTop || document.body.scrollTop);
	return b;
}, getPositionLocal:function (a) {
	if (typeof a == "string") {
		a = document.getElementById(a);
	} else {
		if (typeof a == "object" && typeof a == "jQuery") {
			a = a.get(0);
		}
	}
	var b = a.ownerDocument;
	if (a.parentNode === null || a.style.display == "none") {
		return false;
	}
	var c = null, d = [];
	if (a.getBoundingClientRect) {
		c = a.getBoundingClientRect();
		d = Math.max(b.documentElement.scrollTop, b.body.scrollTop);
		a = c.left + Math.max(b.documentElement.scrollLeft, b.body.scrollLeft) - b.documentElement.clientLeft;
		b = c.top + d - b.documentElement.clientTop;
		if (document.all) {
			a--;
			b--;
		}
		return {x:a, y:b};
	} else {
		if (b.getBoxObjectFor) {
			c = b.getBoxObjectFor(a);
			b = a.style.borderLeftWidth ? parseInt(a.style.borderLeftWidth) : 0;
			d = a.style.borderTopWidth ? parseInt(a.style.borderTopWidth) : 0;
			d = [c.x - b, c.y - d];
		}
	}
	for (c = a.parentNode ? a.parentNode : null; c && c.tagName != "BODY" && c.tagName != "HTML"; ) {
		d[0] -= c.scrollLeft;
		d[1] -= c.scrollTop;
		c = c.parentNode ? c.parentNode : null;
	}
	return {x:d[0], y:d[1]};
}, viewPos:function (a, b) {
	var c = b.UI.offsetWidth, d = b.UI.offsetHeight, e = a.offsetWidth, f = a.offsetHeight, j = document.body, n = j.clientWidth + j.scrollLeft;
	j = j.clientHeight + j.scrollTop;
	var r = this.getPositionLocal(a), p = r.x, x = r.y + f;
	if (p + c + 5 >= n) {
		p = p - c + e;
	}
	if (x + d + 5 >= j) {
		x = x - d - f - 1;
	}
	r.x = p;
	r.y = x;
	return r;
}, cancelEvent:function (a) {
	a = a || window.event;
	if (a.stopPropagation) {
		a.stopPropagation();
	} else {
		a.cancelBubble = true;
	}
}, init:function () {
	if (this.menuInit) {
		return false;
	}
	var a = this, b = document;
	document.all && b.execCommand("BackgroundImageCache", false, true);
	$(b).bind("mousedown", function () {
		a.hideMenu();
	});
	this.menuInit = true;
}, enableMenu:function (a) {
	if (a = document.getElementById(a)) {
		a.disabled = false;
		a.className = iPopupMenu.prototype.classes.li;
	}
}, disableMenu:function (a) {
	if (a = document.getElementById(a)) {
		a.disabled = true;
		a.className = iPopupMenu.prototype.classes.liDis;
	}
}, hideMenu:function () {
	for (var a, b = 0; b < this.menuList.length; b++) {
		(a = this.menuList[b]) && a.hide();
	}
}, makeMenu:function (a) {
	iMenu.init();
	a = new iPopupMenu(a.menu, null, null, a.width ? a.width : 150);
	iMenu.menuList.push(a);
	return a;
}, ContextMenu:function (a) {
	this.cm_prop = a;
	this.cm_menu = iMenu.makeMenu(a);
}, PopupMenu:function (a) {
	this.pp_prop = a;
	this.pp_menu = iMenu.makeMenu(a);
}};
iMenu.ContextMenu.prototype = {show:function (a, b) {
	if (!a) {
		alert("\u672a\u53d1\u73b0\u6709\u6548\u7684\u4e8b\u4ef6\u89e6\u53d1\u5bf9\u8c61\uff01");
		return false;
	}
	b = b || window.event;
	iMenu.cancelEvent(b);
	this.cm_menu.setTargetEle(a);
	this.cm_menu.popUp(iMenu.absEvent(b).x, iMenu.absEvent(b).y);
	this.cm_prop.rule && typeof this.cm_prop.rule == "function" && this.cm_prop.rule(a);
	return false;
}, hide:function () {
	this.cm_menu && this.cm_menu.hide();
}};
iMenu.PopupMenu.prototype = {show:function (a, b) {
	if (!a) {
		alert("\u672a\u53d1\u73b0\u6709\u6548\u7684\u4e8b\u4ef6\u89e6\u53d1\u5bf9\u8c61\uff01");
		return false;
	}
	b = b || window.event;
	iMenu.cancelEvent(b);
	this.pp_menu.setTargetEle(a);
	var c = iMenu.viewPos(a, this.pp_menu);
	this.pp_menu.popUp(c.x, c.y);
	this.pp_prop.rule && typeof this.pp_prop.rule == "function" && this.pp_prop.rule(a);
	return false;
}, hide:function () {
	this.pp_menu && this.pp_menu.hide();
}};
$.LoadingTip = {};
$.LoadingTip.topWin = function () {
	for (var a = window; a.parent && a.parent != a; ) {
		try {
			if (a.parent.document.domain != document.domain) {
				break;
			}
		}
		catch (b) {
			break;
		}
		a = a.parent;
	}
	return a;
};
$.LoadingTip.show = function (a) {
	a = $.extend({id:null, msg:"\u5904\u7406\u4e2d\uff0c\u8bf7\u7b49\u5f85...", width:null, color:null, borderColor:null, opacity:null, autoClose:false}, a);
	var b = this.topWin().document;
	if (a.id) {
		if (b.getElementById("loadingTip-" + a.id)) {
			$("#tipMsg-" + a.id, b).html(a.msg);
			b = $("#tipPanel-" + a.id, b);
			b.css({marginLeft:-(parseInt(b.width() / 2, 10) + 10) + "px", marginTop:-parseInt(b.height() / 2, 10) + "px"});
			a.autoClose && this.setAutoClose(a);
			return false;
		}
	}
	b = $("<div class=\"loading-tip\" oncontextmenu=\"return false;\"/>", b).appendTo($("body", b));
	a.id && b.attr("id", "loadingTip-" + a.id);
	b.html("<div class=\"tip-layer\">" + ($browser.isIE6 ? "<iframe src=\"javascript:void(0);\" style=\"filter:alpha(opacity=0);\" frameborder=\"0\" width=\"100%\" height=\"100%\" srcolling=\"no\"></iframe>" : "&nbsp;") + "</div>" + ("<div" + (a.id ? " id=\"tipPanel-" + a.id + "\"" : "") + " class=\"tip-panel\"><div" + (a.id ? " id=\"tipMsg-" + a.id + "\"" : "") + " class=\"tip-msg\">" + a.msg + "</div></div>")).show();
	b = $("div.tip-panel", b);
	b.css({marginLeft:-(parseInt(b.width() / 2, 10) + 10) + "px", marginTop:-parseInt(b.height() / 2, 10) + "px"});
	a.autoClose && this.setAutoClose(a);
};
$.LoadingTip.setAutoClose = function (a) {
	var b = this, c = null;
	c != null && window.clearTimeout(c);
	c = window.setTimeout(function () {
		b.hide(a.id);
		b = null;
		window.clearTimeout(c);
		c = null;
	}, parseInt(a.autoClose, 10) * 1000);
};
$.LoadingTip.hide = function (a, b) {
	var c = this.topWin();
	a ? $("#loadingTip-" + a, c.document).fadeOut("fast", function () {
		$(this).html("").remove();
		b && $.isFunction(b) && b();
	}) : $("div.loading-tip", c.document).fadeOut("fast", function () {
		$(this).html("").remove();
		b && $.isFunction(b) && b();
	});
	c = null;
};
 