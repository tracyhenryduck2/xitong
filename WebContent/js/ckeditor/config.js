/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.extraPlugins += (config.extraPlugins ? ',lineheight' : 'lineheight'); CKEDITOR.config.toolbar_Full = [ { name: 'document', items : [ 'Source','-','Save','NewPage','DocProps','Preview','Print','-','Templates' ] }, { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] }, { name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] }, { name: 'forms', items : [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] }, '/', { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] }, { name:'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] }, { name: 'links', items : [ 'Link','Unlink','Anchor' ] }, { name:'insert', items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe' ] }, '/', { name: 'styles', items : [ 'Styles','Format','Font','FontSize','lineheight' ] }, { name: 'colors', items : [ 'TextColor','BGColor' ] }, { name: 'tools', items : [ 'Maximize', 'ShowBlocks','-','About' ] } ];
	//config.filebrowserUploadUrl = g_Path+'/admin/common/ckUploadImage.jsp?type=File';  
    config.filebrowserImageUploadUrl = g_Path+'/CkUpload!upload.action?type=Image';  
    config.filebrowserFlashUploadUrl = g_Path+'/admin/common/ckUploadImage.jsp?type=Flash';  
    config.removeDialogTabs = 'image:advanced';
    // 图片浏览配置  
    //config.filebrowserImageBrowseUrl = 'browerServer.do?type=image';  
};
