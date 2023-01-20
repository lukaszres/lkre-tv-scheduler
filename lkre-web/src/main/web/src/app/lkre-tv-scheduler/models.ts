export class Seance {
  title: string;
  time: Date;
  genre: string;
  episode: string;
  channel: string;

  deserialize(input: any): this {
    Object.assign(this, input);
    return this;
  }
}
