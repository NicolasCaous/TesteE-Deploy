$( "#form" ).on('submit', function(e){
  e.preventDefault();

  $.ajax({
    method: 'GET',
    url: "/api/cities?cityQuery=" 
      + encodeURIComponent((((jQuery.trim($( "#cidade" ).val())).length == 0) ? 'none' : $( "#cidade" ).val()))
      + "&stateQuery="
      + encodeURIComponent((((jQuery.trim($( "#estado" ).val())).length == 0) ? 'none' : $( "#estado" ).val())),
    beforeSend: function(request) {
      $.QueryString.cidade = encodeURIComponent((((jQuery.trim($( "#cidade" ).val())).length == 0) ? 'none' : $( "#cidade" ).val()));
      $.QueryString.estado = encodeURIComponent((((jQuery.trim($( "#estado" ).val())).length == 0) ? 'none' : $( "#estado" ).val()));
      updateBrowserQueryString();
    },
    success: function (res) {
      $( ".deletar" ).remove();
      $.each(res, function (i, element) {
        $( "#tabela tr:last" ).after(
          '<tr class="deletar"><td>' + element["state"] + '</td><td>' + element["name"] + '</td><td><div class="visualizar"><a href="javascript:void(0)" onclick="var cidade = $(this).data(\'cidade\'); var estado = $(this).data(\'estado\'); $.ajax({ method: \'GET\', url: \'/api/cities/score?name=\' + encodeURIComponent(cidade) + \'&state=\' + encodeURIComponent(estado), success: function (res) { alert(\'A pontuação da Cidade \' + cidade + \' é: \' + res); }});" data-cidade="' + element["name"] + '" data-estado="' + element["state"] + '">Ver Pontuação</a></div></td></tr>'
        );
      });
    }
  });
});

var updateBrowserQueryString = function() {
  history.replaceState({}, '', "?" + $.param($.QueryString));
};

(function($) {
  /* https://stackoverflow.com/questions/901115/how-can-i-get-query-string-values-in-javascript/901144#answer-3855394 */
  $.QueryString = (function(paramsArray) {
    let params = {};
    for (let i = 0; i < paramsArray.length; ++i) {
      let param = paramsArray[i]
        .split('=', 2);
      if (param.length !== 2)
        continue;
      params[param[0]] = decodeURIComponent(param[1].replace(/\+/g, " "));
    }
    return params;
  })(window.location.search.substr(1).split('&'))
})(jQuery);

$(document).ready(function(){
  $( "#cidade" ).val(decodeURIComponent(($.QueryString.cidade == 'none' || $.QueryString.cidade == null) ? "" : $.QueryString.cidade));
  $( "#estado" ).val(decodeURIComponent(($.QueryString.estado == 'none' || $.QueryString.estado == null) ? "" : $.QueryString.estado));
  $( "#form" ).submit();
});