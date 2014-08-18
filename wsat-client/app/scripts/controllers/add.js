'use strict';

angular.module('angClientApp')
  .controller('AddCtrl', function ($scope, $upload, $location, Auth) {

  		var accountId = Auth.user.accountId;

    	$scope.onRESTSelect = function($files) {
            $scope.err = null;
            //$files: an array of files selected, each file has name, size, and type.
            for (var i = 0; i < $files.length; i++) {
                var file = $files[i];
                if (file.size > 1 * 1024 * 1024) {
                    $scope.err.push({msg: "File exceeeds the 1MB limit."});
                    break;
                }
                console.log(file);
                if (file.type.indexOf('text/xml') === -1) {
                    $scope.err.push({msg: "File is not an xml file."});
                    break;
                }
                
                if (file) {
                    var data = {};
                    $scope.upload = $upload.upload({
                        url: "http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/algoRESTService/WADLParse", //upload.php script, node.js route, or servlet url
                        method: "POST",
                        // headers: {'headerKey': 'headerValue'},
                        // withCredential: true,
                        data: data,
                        file: file,
                        // file: $files, //upload multiple files, this feature only works in HTML5 FromData browsers
                        /* set file formData name for 'Content-Desposition' header. Default: 'file' */
                        //fileFormDataName: myFile, //OR for HTML5 multiple upload only a list: ['name1', 'name2', ...]
                        /* customize how data is added to formData. See #40#issuecomment-28612000 for example */
                        //formDataAppender: function(formData, key, val){} //#40#issuecomment-28612000
                    }).progress(function(evt) {
                        // console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
                    }).success(function(data, status, headers, config) {
                        // file is uploaded successfull
                        console.log("success");
                        console.log(data);
                        $location.path('/services');
                    });
                }
            }
        }

        $scope.onSOAPSelect = function($files) {
            $scope.err = null;
            //$files: an array of files selected, each file has name, size, and type.
            for (var i = 0; i < $files.length; i++) {
                var file = $files[i];
                if (file.size > 1 * 1024 * 1024) {
                    $scope.err.push({msg: "File exceeeds the 1MB limit."});
                    break;
                }
                console.log(file);
                if (file.type.indexOf('text/xml') === -1) {
                    $scope.err.push({msg: "File is not an xml file."});
                    break;
                }
                
                if (file) {
                    var data = {};
                    $scope.upload = $upload.upload({
                        url: "http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/algoSOAPService/WSDLParse", //upload.php script, node.js route, or servlet url
                        method: "POST",
                        // headers: {'headerKey': 'headerValue'},
                        // withCredential: true,
                        data: data,
                        file: file,
                        // file: $files, //upload multiple files, this feature only works in HTML5 FromData browsers
                        /* set file formData name for 'Content-Desposition' header. Default: 'file' */
                        //fileFormDataName: myFile, //OR for HTML5 multiple upload only a list: ['name1', 'name2', ...]
                        /* customize how data is added to formData. See #40#issuecomment-28612000 for example */
                        //formDataAppender: function(formData, key, val){} //#40#issuecomment-28612000
                    }).progress(function(evt) {
                        // console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
                    }).success(function(data, status, headers, config) {
                        // file is uploaded successfull
                        console.log("success");
                        console.log(data);
                        $location.path('/services');
                    });
                }
            }
        }

  });
