//precision mediump float;
varying vec2 v_texCoord0;
varying vec4 v_color;
uniform sampler2D u_texture;

void main() {
	vec4 diffuse=texture2D(u_texture, v_texCoord0);
	
    gl_FragColor.rgba = diffuse*vec4(1,0,0,1);
}