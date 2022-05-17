function add_field() {

    var x = document.getElementById("form");
    // создаем новое поле ввода
    var new_field = document.createElement("input");
    // установим для поля ввода тип данных 'text'
    new_field.setAttribute("type", "text");
    // установим имя для поля ввода
    new_field.setAttribute("name", "subThemes[]");
    new_field.setAttribute("class", "admin__service__buttons__each")
    new_field.setAttribute("placeholder", "Подтема")
    // определим место вствки нового поля ввода (перед каким элементом его вставить)
    var pos = x.childElementCount;

    // добавим поле ввода в форму
    x.insertBefore(new_field, x.childNodes[pos]);
}