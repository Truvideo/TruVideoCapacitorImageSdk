export interface TruvideoSdkImagePlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
}
