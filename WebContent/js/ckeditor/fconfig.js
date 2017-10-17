/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar = 'MyToolbar';   
  
    config.toolbar_MyToolbar =   
    [ 
     ['Bold','Italic','Underline','Strike'], ['Cut','Copy','Paste'], 
      ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'], 
      ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'] 
    ];   
	//config.filebrowserUploadUrl = g_Path+'/admin/common/ckUploadImage.jsp?type=File';  
    config.filebrowserImageUploadUrl = g_Path+'/CkUpload!upload.action?type=Image';  
    config.filebrowserFlashUploadUrl = g_Path+'/admin/common/ckUploadImage.jsp?type=Flash';  
    config.removeDialogTabs = 'image:advanced';
    // å¾çæµè§éç½®  
    //config.filebrowserImageBrowseUrl = 'browerServer.do?type=image';  
};
