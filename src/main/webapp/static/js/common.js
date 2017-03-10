  (function() {
    var $ = (selector) => document.querySelector(selector);
    // get csrf token
    var token  = '';
    fetch('/api/auth/csrf-token', {credentials: 'include'}).then(function(r) {
      return r.json();
    }).then(function(response) {
      if (response.isOK) {
        token = response.msg.csrfToken;
      }
    }).then(function() {
      
      // 获取信息
      var apiUrl = document.body.getAttribute('data-api');
      fetch(apiUrl + '?_csrf=' + token, {credentials: 'include', method: 'POST'}).then(function(r){
        return r.json()
      }).then(function(response){
        if (response.isOK) {
          var msg = response.msg;
          for (var key in msg) {
            $('.' + key).innerHTML = msg[key];
          }
          $('.logout').style.display = 'inline';
          /* alert('username: ' + user.username + '\n' + 'nickname: ' + user.nickname); */
        } else {
          alert('拒绝访问!');
          location.href = '/welcome.html';
        }
      }).catch(function(error) {
        console.info(error);
      });
      
      $('.logout').addEventListener('click', function(event) {
        fetch('/api/auth/logout?_csrf=' + token, {credentials: 'include', method: 'POST'}).then(function(r){
          return r.json();
        }).then(function(response) {
          if (response.isOK) {
            alert('退出登录成功!');
            location.href = '/index.html';
          }
        });
      });
    });
  })();