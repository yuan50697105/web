window.onload = function () {
    function initMap() {
        createMap();
        setMapEvent();
        addMapControl();
        addMapOverlay();
    }

    function createMap() {
        map = new BMap.Map("map");
        map.centerAndZoom(new BMap.Point(114.374516, 30.895464), 17);
    }

    function setMapEvent() {
        map.enableScrollWheelZoom();
        map.enableKeyboard();
        map.enableDragging();
        map.enableDoubleClickZoom()
    }

    function addClickHandler(target, window) {
        target.addEventListener("click", function () {
            target.openInfoWindow(window);
        });
    }

    function addMapOverlay() {
        var markers = [
            {
                content: "",
                title: "",
                imageOffset: {width: -23, height: -21},
                position: {lat: 30.895867, lng: 114.374768}
            }
        ];
        for (var index = 0; index < markers.length; index++) {
            var point = new BMap.Point(markers[index].position.lng, markers[index].position.lat);
            var marker = new BMap.Marker(point, {
                icon: new BMap.Icon("http://api.map.baidu.com/lbsapi/createmap/images/icon.png", new BMap.Size(20, 25), {
                    imageOffset: new BMap.Size(markers[index].imageOffset.width, markers[index].imageOffset.height)
                })
            });
            var label = new BMap.Label(markers[index].title, {offset: new BMap.Size(25, 5)});
            var opts = {
                width: 200,
                title: markers[index].title,
                enableMessage: false
            };
            var infoWindow = new BMap.InfoWindow(markers[index].content, opts);
            marker.setLabel(label);
            addClickHandler(marker, infoWindow);
            map.addOverlay(marker);
        }
        ;
    }

    function addMapControl() {
        var navControl = new BMap.NavigationControl({
            anchor: BMAP_ANCHOR_TOP_LEFT,
            type: BMAP_NAVIGATION_CONTROL_LARGE
        });
        map.addControl(navControl);
    }

    var map;
    initMap();
}
