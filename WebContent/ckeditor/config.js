/**
 * @license Copyright (c) 2003-2021, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	
	config.removePlugins = 'forms,table,tableselection,tabletools,templates,scayt,div';
	
	config.toolbarCanCollapse = true;
	config.toolbarStartupExpanded = false;
	config.filebrowserUploadUrl = 'boardImageUpload'
	config.youtube_responsive = true;
	
	config.extraPlugins = 'wordcount,notification';
	config.wordcount = {
	    showParagraphs: false,
	    showCharCount: true,
	    countSpacesAsChars: true,
	    countHTML: true,
	    maxCharCount: 4000
	};
};

CKEDITOR.on( 'dialogDefinition', function( ev ){
  var dialogName = ev.data.name;
  var dialogDefinition = ev.data.definition;
  var dialog = ev.data.definition.dialog;

  if ( dialogName == 'image' ){
    dialog.on('show', function (obj) {
    	this.selectPage('Upload'); // 업로드 탭으로 시작되도록 함
    });

    var infoTab = dialogDefinition.getContents( 'info' ); //info탭을 제거하면 이미지 업로드가 안된다.
    // info 탭 내에 불필요한 엘레멘트들 제거
    infoTab.remove('txtAlt');    // 대체 문자열
    //infoTab.remove('txtHSpace'); // 세로 여백
    //infoTab.remove('txtVSpace'); // 가로 여백
    //infoTab.remove('txtBorder'); // 테두리
    //infoTab.remove('txtWidth');  // 너비
    //infoTab.remove('txtHeight'); // 높이
    //infoTab.remove('ratioLock'); // 비율유지
    infoTab.remove('cmbAlign');  // 정렬
  }
});