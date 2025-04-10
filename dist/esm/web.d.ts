import { WebPlugin } from '@capacitor/core';
import type { TruvideoSdkImagePlugin } from './definitions';
export declare class TruvideoSdkImageWeb extends WebPlugin implements TruvideoSdkImagePlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
}
