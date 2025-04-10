'use strict';

var core = require('@capacitor/core');

const TruvideoSdkImage = core.registerPlugin('TruvideoSdkImage', {
    web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.TruvideoSdkImageWeb()),
});

class TruvideoSdkImageWeb extends core.WebPlugin {
    async echo(options) {
        console.log('ECHO', options);
        return options;
    }
}

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    TruvideoSdkImageWeb: TruvideoSdkImageWeb
});

exports.TruvideoSdkImage = TruvideoSdkImage;
//# sourceMappingURL=plugin.cjs.js.map
