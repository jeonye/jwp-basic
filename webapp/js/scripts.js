String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

// Button Event
$(".answerWrite input[type=submit]").click(addAnswer);
$(".qna-comment .link-delete-article").click(deleteAnswer);


// Event Function
// 답변 추가
function addAnswer(e) {
  e.preventDefault();
  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dateType : 'json',
    error : onError,
    success : onAddSuccess
  });
}

// 답변 삭제
function deleteAnswer(e) {
  e.preventDefault();
  var queryString = $("form[name=answerDeleteForm]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/deleteAnswer',
    data : queryString,
    dateType : 'json',
    error : onError,
    success : onDeleteSuccess
  });
}

function onAddSuccess(json, status) {
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(json.writer, new Date(json.createDate), json.contents, json.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
}

function onDeleteSuccess(data, status) {
  var answerId = data;
  var articleHtml = $("input[name=answerId][value="+answerId+"]").parents("article");
  articleHtml.remove();
}
