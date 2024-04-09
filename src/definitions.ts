export interface CompassPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  check(): boolean;
  start(): Promise<void>;
  stop(): void;
  getOrientation(event: any):any;
}
