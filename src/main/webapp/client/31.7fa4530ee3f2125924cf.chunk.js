webpackJsonp([31],{qc2Y:function(t,e,n){"use strict";function r(t){return o["\u0275vid"](0,[(t()(),o["\u0275ted"](null,["\n    "])),(t()(),o["\u0275eld"](0,null,null,4,"div",[["class","org"]],null,null,null,null,null)),(t()(),o["\u0275ted"](null,["\n      "])),(t()(),o["\u0275eld"](8388608,null,null,1,"router-outlet",[],null,null,null,null,null)),o["\u0275did"](73728,null,0,_.n,[_.o,o.ViewContainerRef,o.ComponentFactoryResolver,[8,null]],null,null),(t()(),o["\u0275ted"](null,["\n    "])),(t()(),o["\u0275ted"](null,["\n  "]))],null,null)}Object.defineProperty(e,"__esModule",{value:!0});var o=n("/oeL"),i=function(){return function(){}}(),l=n("qbdv"),_=n("BkNc"),a=n("bm2B"),u=n("9Qcf"),s=n("maBJ"),c=n("R08E"),h=n("Qg/J"),p=n("WDs4"),d=n("jk5u"),g=n("g5gQ"),f=n("mtQK"),v=n("Rhg7"),y=n("pNB/"),b=n("2kBf"),m=(n("82j9"),n("ZV8k")),j=n("oRYE"),M=n("kUuJ"),O=n("qP5l"),R=n("Syzy"),w=n("Icst"),P=function(){function t(t,e,n,r,o,i){this.location=t,this._state=e,this._sockService=n,this.userService=r,this.orgService=o,this.router=i}return t.prototype.canActivate=function(t,e){var n=M.b.getOrgAndPrjId(e.url);return console.log("OrgResolve - canActivate",e.url,n),n.orgId==j.a.CURR_ORG_ID||(this.willNotChangePrj(n)||!n.prjId?this.orgService.change(n.orgId).toPromise().then(function(t){return j.a.CURR_ORG_ID=n.orgId,!0}):(j.a.CURR_ORG_ID=n.orgId,!0))},t.prototype.willNotChangePrj=function(t){return t.prjId==j.a.CURR_PRJ_ID},t.ctorParameters=function(){return[{type:l.Location},{type:m.a},{type:O.a},{type:R.a},{type:w.a},{type:_.j}]},t}(),S=function(){function t(t){this._route=t}return t.prototype.ngOnInit=function(){},t.ctorParameters=function(){return[{type:_.a}]},t}(),T=o["\u0275crt"]({encapsulation:2,styles:[],data:{}}),I=o["\u0275ccf"]("org",S,function(t){return o["\u0275vid"](0,[(t()(),o["\u0275eld"](0,null,null,1,"org",[],null,null,null,r,T)),o["\u0275did"](57344,null,0,S,[_.a],null,null)],function(t,e){t(e,1,0)},null)},{},{},[]),N=n("9GFz"),C=n("KBuQ"),L=n("CPp0"),A=n("a3e3");n.d(e,"OrgModuleNgFactory",function(){return E});var F=this&&this.__extends||function(){var t=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,e){t.__proto__=e}||function(t,e){for(var n in e)e.hasOwnProperty(n)&&(t[n]=e[n])};return function(e,n){function r(){this.constructor=e}t(e,n),e.prototype=null===n?Object.create(n):(r.prototype=n.prototype,new r)}}(),B=function(t){function e(e){return t.call(this,e,[I],[])||this}return F(e,t),Object.defineProperty(e.prototype,"_NgLocalization_17",{get:function(){return null==this.__NgLocalization_17&&(this.__NgLocalization_17=new l.NgLocaleLocalization(this.parent.get(o.LOCALE_ID))),this.__NgLocalization_17},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"_\u0275i_18",{get:function(){return null==this.__\u0275i_18&&(this.__\u0275i_18=new a.y),this.__\u0275i_18},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"_FormBuilder_19",{get:function(){return null==this.__FormBuilder_19&&(this.__FormBuilder_19=new a.e),this.__FormBuilder_19},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"_AccountService_21",{get:function(){return null==this.__AccountService_21&&(this.__AccountService_21=new y.a(this.parent.get(N.a),this.parent.get(C.a))),this.__AccountService_21},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"_ProjectService_22",{get:function(){return null==this.__ProjectService_22&&(this.__ProjectService_22=new b.a(this.parent.get(N.a),this.parent.get(m.a))),this.__ProjectService_22},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"_OrgResolve_23",{get:function(){return null==this.__OrgResolve_23&&(this.__OrgResolve_23=new P(this.parent.get(l.Location),this.parent.get(m.a),this.parent.get(O.a),this.parent.get(R.a),this.parent.get(w.a),this.parent.get(_.j))),this.__OrgResolve_23},enumerable:!0,configurable:!0}),e.prototype.createInternal=function(){return this._CommonModule_0=new l.CommonModule,this._RouterModule_1=new _.m(this.parent.get(_.s,null),this.parent.get(_.j,null)),this._\u0275ba_2=new a.v,this._FormsModule_3=new a.i,this._ReactiveFormsModule_4=new a.s,this._TranslateModule_5=new u.b,this._TranslateStore_6=new s.a,this._TranslateLoader_7=d.b(this.parent.get(L.e)),this._TranslateParser_8=new c.a,this._MissingTranslationHandler_9=new h.a,this._USE_STORE_10=void 0,this._TranslateService_11=new p.a(this._TranslateStore_6,this._TranslateLoader_7,this._TranslateParser_8,this._MissingTranslationHandler_9,this._USE_STORE_10),this._AppTranslationModule_12=new d.a(this._TranslateService_11),this._NgbDropdownModule_13=new g.a,this._PipeModule_14=new f.a,this._NgaModule_15=new v.a,this._OrgModule_16=new i,this._ROUTES_20=[[{path:":orgId",component:S,canActivate:[P],children:[{path:"prjs",loadChildren:"../../project/project/project.module#ProjectModule"},{path:"prj",loadChildren:"./prj/prj.module#PrjModule"}]}]],this._OrgModule_16},e.prototype.getInternal=function(t,e){return t===l.CommonModule?this._CommonModule_0:t===_.m?this._RouterModule_1:t===a.v?this._\u0275ba_2:t===a.i?this._FormsModule_3:t===a.s?this._ReactiveFormsModule_4:t===u.b?this._TranslateModule_5:t===s.a?this._TranslateStore_6:t===A.b?this._TranslateLoader_7:t===c.b?this._TranslateParser_8:t===h.b?this._MissingTranslationHandler_9:t===p.b?this._USE_STORE_10:t===p.a?this._TranslateService_11:t===d.a?this._AppTranslationModule_12:t===g.a?this._NgbDropdownModule_13:t===f.a?this._PipeModule_14:t===v.a?this._NgaModule_15:t===i?this._OrgModule_16:t===l.NgLocalization?this._NgLocalization_17:t===a.y?this._\u0275i_18:t===a.e?this._FormBuilder_19:t===_.h?this._ROUTES_20:t===y.a?this._AccountService_21:t===b.a?this._ProjectService_22:t===P?this._OrgResolve_23:e},e.prototype.destroyInternal=function(){},e}(o["\u0275NgModuleInjector"]),E=new o.NgModuleFactory(B,i)}});