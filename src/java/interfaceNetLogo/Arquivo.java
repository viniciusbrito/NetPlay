/*
 * Arquivo.java
 *
 * Created em 9 de Julho de 2006, 09:57
 */

package interfaceNetLogo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import utilitario.*;

import cdp.Modelo;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class Arquivo {
    
    int idModelo;
    
    Codigo codigo;
    Graficos graficos;
    
    public Arquivo(int id)
    {
        idModelo = id;
        codigo = new Codigo(idModelo);
        graficos = new Graficos (idModelo);
    }
    
    public String getArquivo( )
    {
        String separador = "@#$#@#$#@\n";
        
        String arquivo;
        arquivo = codigo.getCodigo() +
                separador +
                graficos.getGraficos() +
                separador +
                verDescricaoModelo() + "\n" +
                separador +
                configuracoesNetLogo();
        return arquivo;
    }
    
    private String verDescricaoModelo ( )
    {
        Session sessao = HibernateUtility.getSession();
        Transaction t = sessao.beginTransaction();
       
        Query consulta = sessao.createQuery("from Modelo m where m.idModelo= :id");
        consulta.setInteger("id", idModelo);
        List lista = consulta.list();
        Modelo modelo = (Modelo) lista.get(0);
        
        String descricaoModelo = modelo.getDescricao();
        
        t.commit();
        sessao.close();
        
        descricaoModelo = "DESCRICAO DO MODELO\n" +
                "-------------------\n" + descricaoModelo;
        return descricaoModelo;
    }
        
    private String configuracoesNetLogo ( )
    {
        return declaracaoFormas() +
                declaracaoFormas2() + 
                "@#$#@#$#@\n"+
                "NetLogo 3.1.2\n"+
                "@#$#@#$#@\n"+
                "@#$#@#$#@\n"+
                "@#$#@#$#@\n"+
                "@#$#@#$#@\n\n";
    }
    
    private String declaracaoFormas ( )
    {
        String formas =
                "default\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 5 40 250 150 205 260 250\n" + 	   
                "\n" + 	   
                "airplane\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 0 135 15 120 60 120 105 15 165 15 195 120 180 135 240 105 270 120 285 150 270 180 285 210 270 165 240 180 180 285 195 285 165 180 105 180 60 165 15\n" + 	   
                "\n" + 	   
                "airplane 2\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 26 135 30 120 60 120 90 18 105 15 135 120 150 120 165 135 210 135 225 150 285 165 225 165 210 180 165 180 150 285 135 282 105 180 90 180 60 165 30\n" + 	   
                "Line -7500403 false 120 30 180 30\n" + 	   
                "Polygon -7500403 true true 105 255 120 240 180 240 195 255 180 270 120 270\n" + 	   
                "\n" + 	   
                "ambulance\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 30 90 210 195\n" + 	   
                "Polygon -7500403 true true 296 190 296 150 259 134 244 104 210 105 210 190\n" + 	   
                "Rectangle -1 true false 195 60 195 105\n" + 	   
                "Polygon -16777216 true false 238 112 252 141 219 141 218 112\n" + 	   
                "Circle -16777216 true false 234 174 42\n" + 	   
                "Circle -16777216 true false 69 174 42\n" + 	   
                "Rectangle -1 true false 288 158 297 173\n" + 	   
                "Rectangle -1184463 true false 289 180 298 172\n" + 	   
                "Rectangle -2674135 true false 29 151 298 158\n" + 	   
                "Line -16777216 false 210 90 210 195\n" + 	   
                "Rectangle -16777216 true false 83 116 128 133\n" + 	   
                "Rectangle -16777216 true false 153 111 176 134\n" + 	   
                "Line -7500403 true 165 105 165 135\n" + 	   
                "Rectangle -7500403 true true 14 186 33 195\n" + 	   
                "Line -13345367 false 45 135 75 120\n" + 	   
                "Line -13345367 false 75 135 45 120\n" + 	   
                "Line -13345367 false 60 112 60 142\n" + 	   
                "\n" + 	   
                "ant\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 136 61 129 46 144 30 119 45 124 60 114 82 97 37 132 10 93 36 111 84 127 105 172 105 189 84 208 35 171 11 202 35 204 37 186 82 177 60 180 44 159 32 170 44 165 60\n" + 	   
                "Polygon -7500403 true true 150 95 135 103 139 117 125 149 137 180 135 196 150 204 166 195 161 180 174 150 158 116 164 102\n" + 	   
                "Polygon -7500403 true true 149 186 128 197 114 232 134 270 149 282 166 270 185 232 171 195 149 186\n" + 	   
                "Polygon -7500403 true true 225 66 230 107 159 122 161 127 234 111 236 106\n" + 	   
                "Polygon -7500403 true true 78 58 99 116 139 123 137 128 95 119\n" + 	   
                "Polygon -7500403 true true 48 103 90 147 129 147 130 151 86 151\n" + 	   
                "Polygon -7500403 true true 65 224 92 171 134 160 135 164 95 175\n" + 	   
                "Polygon -7500403 true true 235 222 210 170 163 162 161 166 208 174\n" + 	   
                "Polygon -7500403 true true 249 107 211 147 168 147 168 150 213 150\n" + 	   
                "\n" + 	   
                "apple\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 33 58 0 150 30 240 105 285 135 285 150 270 165 285 195 285 255 255 300 150 268 62 226 43 194 36 148 32 105 35\n" + 	   
                "Line -16777216 false 106 55 151 62\n" + 	   
                "Line -16777216 false 157 62 209 57\n" + 	   
                "Polygon -6459832 true false 152 62 158 62 160 46 156 30 147 18 132 26 142 35 148 46\n" + 	   
                "Polygon -16777216 false false 132 25 144 38 147 48 151 62 158 63 159 47 155 30 147 18\n" + 	   
                "\n" + 	   
                "arrow\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150\n" + 	   
                "\n" + 	   
                "arrow 3\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 135 255 105 300 105 225 135 195 135 75 105 90 150 0 195 90 165 75 165 195 195 225 195 300 165 255\n" + 	   
                "\n" + 	   
                "ball basketball\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 26 26 247\n" + 	   
                "Polygon -16777216 false false 30 150 30 165 45 195 75 225 120 240 180 240 225 225 255 195 270 165 270 150 270 135 255 105 225 75 180 60 120 60 75 75 45 105 30 135\n" + 	   
                "Line -16777216 false 30 150 270 150\n" + 	   
                "Circle -16777216 false false 26 26 247\n" + 	   
                "\n" + 	   
                "ball tennis\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 30 30 240\n" + 	   
                "Circle -7500403 false true 30 30 240\n" + 	   
                "Polygon -16777216 true false 50 82 54 90 59 107 64 140 64 164 63 189 59 207 54 222 68 236 76 220 81 195 84 163 83 139 78 102 72 83 63 67\n" + 	   
                "Polygon -16777216 true false 250 82 246 90 241 107 236 140 236 164 237 189 241 207 246 222 232 236 224 220 219 195 216 163 217 139 222 102 228 83 237 67\n" + 	   
                "Polygon -1 true false 247 79 243 86 237 106 232 138 232 167 235 199 239 215 244 225 236 234 229 221 224 196 220 163 221 138 227 102 234 83 240 71\n" + 	   
                "Polygon -1 true false 53 79 57 86 63 106 68 138 68 167 65 199 61 215 56 225 64 234 71 221 76 196 80 163 79 138 73 102 66 83 60 71\n" + 	   
                "\n" + 	   
                "balloon\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 73 0 152\n" + 	   
                "Polygon -7500403 true true 219 104 205 133 185 165 174 190 165 210 165 225 150 225 147 119\n" + 	   
                "Polygon -7500403 true true 79 103 95 133 115 165 126 190 135 210 135 225 150 225 154 120\n" + 	   
                "Rectangle -6459832 true false 129 241 173 273\n" + 	   
                "Line -16777216 false 135 225 135 240\n" + 	   
                "Line -16777216 false 165 225 165 240\n" + 	   
                "Line -16777216 false 150 225 150 240\n" + 	   
                "\n" + 	   
                "banana\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 false true 25 78 29 86 30 95 27 103 17 122 12 151 18 181 39 211 61 234 96 247 155 259 203 257 243 245 275 229 288 205 284 192 260 188 249 187 214 187 188 188 181 189 144 189 122 183 107 175 89 158 69 126 56 95 50 83 38 68\n" + 	   
                "Polygon -7500403 true true 39 69 26 77 30 88 29 103 17 124 12 152 18 179 34 205 60 233 99 249 155 260 196 259 237 248 272 230 289 205 284 194 264 190 244 188 221 188 185 191 170 191 145 190 123 186 108 178 87 157 68 126 59 103 52 88\n" + 	   
                "Line -16777216 false 54 169 81 195\n" + 	   
                "Line -16777216 false 75 193 82 199\n" + 	   
                "Line -16777216 false 99 211 118 217\n" + 	   
                "Line -16777216 false 241 211 254 210\n" + 	   
                "Line -16777216 false 261 224 276 214\n" + 	   
                "Polygon -16777216 true false 283 196 273 204 287 208\n" + 	   
                "Polygon -16777216 true false 36 114 34 129 40 136\n" + 	   
                "Polygon -16777216 true false 46 146 53 161 53 152\n" + 	   
                "Line -16777216 false 65 132 82 162\n" + 	   
                "Line -16777216 false 156 250 199 250\n" + 	   
                "Polygon -16777216 true false 26 77 30 90 50 85 39 69\n" + 	   
                "\n" + 	   
                "bee\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -1184463 true false 152 149 77 163 67 195 67 211 74 234 85 252 100 264 116 276 134 286 151 300 167 285 182 278 206 260 220 242 226 218 226 195 222 166\n" + 	   
                "Polygon -16777216 true false 150 149 128 151 114 151 98 145 80 122 80 103 81 83 95 67 117 58 141 54 151 53 177 55 195 66 207 82 211 94 211 116 204 139 189 149 171 152\n" + 	   
                "Polygon -7500403 true true 151 54 119 59 96 60 81 50 78 39 87 25 103 18 115 23 121 13 150 1 180 14 189 23 197 17 210 19 222 30 222 44 212 57 192 58\n" + 	   
                "Polygon -16777216 true false 70 185 74 171 223 172 224 186\n" + 	   
                "Polygon -16777216 true false 67 211 71 226 224 226 225 211 67 211\n" + 	   
                "Polygon -16777216 true false 91 257 106 269 195 269 211 255\n" + 	   
                "Line -1 false 144 100 70 87\n" + 	   
                "Line -1 false 70 87 45 87\n" + 	   
                "Line -1 false 45 86 26 97\n" + 	   
                "Line -1 false 26 96 22 115\n" + 	   
                "Line -1 false 22 115 25 130\n" + 	   
                "Line -1 false 26 131 37 141\n" + 	   
                "Line -1 false 37 141 55 144\n" + 	   
                "Line -1 false 55 143 143 101\n" + 	   
                "Line -1 false 141 100 227 138\n" + 	   
                "Line -1 false 227 138 241 137\n" + 	   
                "Line -1 false 241 137 249 129\n" + 	   
                "Line -1 false 249 129 254 110\n" + 	   
                "Line -1 false 253 108 248 97\n" + 	   
                "Line -1 false 249 95 235 82\n" + 	   
                "Line -1 false 235 82 144 100\n" + 	   
                "\n" + 	   
                "bike\n" + 	   
                "false\n" + 	   
                "1\n" + 	   
                "Line -7500403 false 163 183 228 184\n" + 	   
                "Circle -7500403 false false 213 184 22\n" + 	   
                "Circle -7500403 false false 156 187 16\n" + 	   
                "Circle -16777216 false false 28 148 95\n" + 	   
                "Circle -16777216 false false 24 144 102\n" + 	   
                "Circle -16777216 false false 174 144 102\n" + 	   
                "Circle -16777216 false false 177 148 95\n" + 	   
                "Polygon -2674135 true true 75 195 90 90 98 92 97 107 192 122 207 83 215 85 202 123 211 133 225 195 165 195 164 188 214 188 202 133 94 116 82 195\n" + 	   
                "Polygon -2674135 true true 208 83 164 193 171 196 217 85\n" + 	   
                "Polygon -2674135 true true 165 188 91 120 90 131 164 196\n" + 	   
                "Line -7500403 false 159 173 170 219\n" + 	   
                "Line -7500403 false 155 172 166 172\n" + 	   
                "Line -7500403 false 166 219 177 219\n" + 	   
                "Polygon -16777216 true false 187 92 198 92 208 97 217 100 231 93 231 84 216 82 201 83 184 85\n" + 	   
                "Polygon -7500403 true true 71 86 98 93 101 85 74 81\n" + 	   
                "Rectangle -16777216 true false 75 75 75 90\n" + 	   
                "Polygon -16777216 true false 70 87 70 72 78 71 78 89\n" + 	   
                "Circle -7500403 false false 153 184 22\n" + 	   
                "Line -7500403 false 159 206 228 205\n" + 	   
                "\n" + 	   
                "bird side\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 0 120 45 90 75 90 105 120 150 120 240 135 285 120 285 135 300 150 240 150 195 165 255 195 210 195 150 210 90 195 60 180 45 135\n" + 	   
                "Circle -16777216 true false 38 98 14\n" + 	   
                "\n" + 	   
                "boat\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -1 true false 63 162 90 207 223 207 290 162\n" + 	   
                "Rectangle -6459832 true false 150 32 157 162\n" + 	   
                "Polygon -13345367 true false 150 34 131 49 145 47 147 48 149 49\n" + 	   
                "Polygon -7500403 true true 158 33 230 157 182 150 169 151 157 156\n" + 	   
                "Polygon -7500403 true true 149 55 88 143 103 139 111 136 117 139 126 145 130 147 139 147 146 146 149 55\n" + 	   
                "\n" + 	   
                "book\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 30 195 150 255 270 135 150 75\n" + 	   
                "Polygon -7500403 true true 30 135 150 195 270 75 150 15\n" + 	   
                "Polygon -7500403 true true 30 135 30 195 90 150\n" + 	   
                "Polygon -1 true false 39 139 39 184 151 239 156 199\n" + 	   
                "Polygon -1 true false 151 239 254 135 254 90 151 197\n" + 	   
                "Line -7500403 true 150 196 150 247\n" + 	   
                "Line -7500403 true 43 159 138 207\n" + 	   
                "Line -7500403 true 43 174 138 222\n" + 	   
                "Line -7500403 true 153 206 248 113\n" + 	   
                "Line -7500403 true 153 221 248 128\n" + 	   
                "Polygon -1 true false 159 52 144 67 204 97 219 82\n" + 	   
                "\n" + 	   
                "bottle\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 90 240 60\n" + 	   
                "Rectangle -1 true false 135 8 165 31\n" + 	   
                "Line -7500403 true 123 30 175 30\n" + 	   
                "Circle -7500403 true true 150 240 60\n" + 	   
                "Rectangle -7500403 true true 90 105 210 270\n" + 	   
                "Rectangle -7500403 true true 120 270 180 300\n" + 	   
                "Circle -7500403 true true 90 45 120\n" + 	   
                "Rectangle -7500403 true true 135 27 165 51\n" + 	   
                "\n" + 	   
                "bowling pin\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 132 285 117 256 105 210 105 165 121 135 136 90 136 75 126 32 125 14 134 5 151 0 168 4 177 12 176 32 166 75 166 90 181 135 195 165 195 210 184 256 170 285\n" + 	   
                "Polygon -2674135 true false 134 68 132 59 170 59 168 68\n" + 	   
                "Polygon -2674135 true false 136 84 135 94 167 94 166 84\n" + 	   
                "\n" + 	   
                "box\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 285 285 225 285 75 150 135\n" + 	   
                "Polygon -7500403 true true 150 135 15 75 150 15 285 75\n" + 	   
                "Polygon -7500403 true true 15 75 15 225 150 285 150 135\n" + 	   
                "Line -16777216 false 150 285 150 135\n" + 	   
                "Line -16777216 false 150 135 15 75\n" + 	   
                "Line -16777216 false 150 135 285 75\n" + 	   
                "\n" + 	   
                "bug\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 96 182 108\n" + 	   
                "Circle -7500403 true true 110 127 80\n" + 	   
                "Circle -7500403 true true 110 75 80\n" + 	   
                "Line -7500403 true 150 100 80 30\n" + 	   
                "Line -7500403 true 150 100 220 30\n" + 	   
                "\n" + 	   
                "building institution\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 0 60 300 270\n" + 	   
                "Rectangle -16777216 true false 130 196 168 256\n" + 	   
                "Rectangle -16777216 false false 0 255 300 270\n" + 	   
                "Polygon -7500403 true true 0 60 150 15 300 60\n" + 	   
                "Polygon -16777216 false false 0 60 150 15 300 60\n" + 	   
                "Circle -1 true false 135 26 30\n" + 	   
                "Circle -16777216 false false 135 25 30\n" + 	   
                "Rectangle -16777216 false false 0 60 300 75\n" + 	   
                "Rectangle -16777216 false false 218 75 255 90\n" + 	   
                "Rectangle -16777216 false false 218 240 255 255\n" + 	   
                "Rectangle -16777216 false false 224 90 249 240\n" + 	   
                "Rectangle -16777216 false false 45 75 82 90\n" + 	   
                "Rectangle -16777216 false false 45 240 82 255\n" + 	   
                "Rectangle -16777216 false false 51 90 76 240\n" + 	   
                "Rectangle -16777216 false false 90 240 127 255\n" + 	   
                "Rectangle -16777216 false false 90 75 127 90\n" + 	   
                "Rectangle -16777216 false false 96 90 121 240\n" + 	   
                "Rectangle -16777216 false false 179 90 204 240\n" + 	   
                "Rectangle -16777216 false false 173 75 210 90\n" + 	   
                "Rectangle -16777216 false false 173 240 210 255\n" + 	   
                "Rectangle -16777216 false false 269 90 294 240\n" + 	   
                "Rectangle -16777216 false false 263 75 300 90\n" + 	   
                "Rectangle -16777216 false false 263 240 300 255\n" + 	   
                "Rectangle -16777216 false false 0 240 37 255\n" + 	   
                "Rectangle -16777216 false false 6 90 31 240\n" + 	   
                "Rectangle -16777216 false false 0 75 37 90\n" + 	   
                "Line -16777216 false 112 260 184 260\n" + 	   
                "Line -16777216 false 105 265 196 265\n" + 	   
                "\n" + 	   
                "building store\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 30 45 45 240\n" + 	   
                "Rectangle -16777216 false false 30 45 45 165\n" + 	   
                "Rectangle -7500403 true true 15 165 285 255\n" + 	   
                "Rectangle -16777216 true false 120 195 180 255\n" + 	   
                "Line -7500403 true 150 195 150 255\n" + 	   
                "Rectangle -16777216 true false 30 180 105 240\n" + 	   
                "Rectangle -16777216 true false 195 180 270 240\n" + 	   
                "Line -16777216 false 0 165 300 165\n" + 	   
                "Polygon -7500403 true true 0 165 45 135 60 90 240 90 255 135 300 165\n" + 	   
                "Rectangle -7500403 true true 0 0 75 45\n" + 	   
                "Rectangle -16777216 false false 0 0 75 45\n" + 	   
                "\n" + 	   
                "bulldozer top\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 195 60 255 255\n" + 	   
                "Rectangle -16777216 false false 195 60 255 255\n" + 	   
                "Rectangle -7500403 true true 45 60 105 255\n" + 	   
                "Rectangle -16777216 false false 45 60 105 255\n" + 	   
                "Line -16777216 false 45 75 255 75\n" + 	   
                "Line -16777216 false 45 105 255 105\n" + 	   
                "Line -16777216 false 45 60 255 60\n" + 	   
                "Line -16777216 false 45 240 255 240\n" + 	   
                "Line -16777216 false 45 225 255 225\n" + 	   
                "Line -16777216 false 45 195 255 195\n" + 	   
                "Line -16777216 false 45 150 255 150\n" + 	   
                "Polygon -1184463 true true 90 60 75 90 75 240 120 255 180 255 225 240 225 90 210 60\n" + 	   
                "Polygon -16777216 false false 225 90 210 60 211 246 225 240\n" + 	   
                "Polygon -16777216 false false 75 90 90 60 89 246 75 240\n" + 	   
                "Polygon -16777216 false false 89 247 116 254 183 255 211 246 211 211 90 210\n" + 	   
                "Rectangle -16777216 false false 90 60 210 90\n" + 	   
                "Rectangle -1184463 true true 180 30 195 90\n" + 	   
                "Rectangle -16777216 false false 105 30 120 90\n" + 	   
                "Rectangle -1184463 true true 105 45 120 90\n" + 	   
                "Rectangle -16777216 false false 180 45 195 90\n" + 	   
                "Polygon -16777216 true false 195 105 180 120 120 120 105 105\n" + 	   
                "Polygon -16777216 true false 105 199 120 188 180 188 195 199\n" + 	   
                "Polygon -16777216 true false 195 120 180 135 180 180 195 195\n" + 	   
                "Polygon -16777216 true false 105 120 120 135 120 180 105 195\n" + 	   
                "Line -1184463 true 105 165 195 165\n" + 	   
                "Circle -16777216 true false 113 226 14\n" + 	   
                "Polygon -1184463 true true 105 15 60 30 60 45 240 45 240 30 195 15\n" + 	   
                "Polygon -16777216 false false 105 15 60 30 60 45 240 45 240 30 195 15\n" + 	   
                "\n" + 	   
                "bus\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 15 206 15 150 15 120 30 105 270 105 285 120 285 135 285 206 270 210 30 210\n" + 	   
                "Rectangle -16777216 true false 36 126 231 159\n" + 	   
                "Line -7500403 false 60 135 60 165\n" + 	   
                "Line -7500403 false 60 120 60 165\n" + 	   
                "Line -7500403 false 90 120 90 165\n" + 	   
                "Line -7500403 false 120 120 120 165\n" + 	   
                "Line -7500403 false 150 120 150 165\n" + 	   
                "Line -7500403 false 180 120 180 165\n" + 	   
                "Line -7500403 false 210 120 210 165\n" + 	   
                "Line -7500403 false 240 135 240 165\n" + 	   
                "Rectangle -16777216 true false 15 174 285 182\n" + 	   
                "Circle -16777216 true false 48 187 42\n" + 	   
                "Rectangle -16777216 true false 240 127 276 205\n" + 	   
                "Circle -16777216 true false 195 187 42\n" + 	   
                "Line -7500403 false 257 120 257 207\n" + 	   
                "\n" + 	   
                "butterfly\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240\n" + 	   
                "Polygon -7500403 true true 150 165 89 198 75 225 75 255 105 270 135 255 150 240\n" + 	   
                "Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163\n" + 	   
                "Polygon -7500403 true true 162 150 200 105 245 90 275 90 290 105 290 135 275 180 260 195 215 195 162 165\n" + 	   
                "Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225\n" + 	   
                "Circle -16777216 true false 135 90 30\n" + 	   
                "Line -16777216 false 150 105 195 60\n" + 	   
                "Line -16777216 false 150 105 105 60\n" + 	   
                "\n" + 	   
                "cactus\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 130 300 124 206 110 207 94 201 81 183 75 171 74 95 79 79 88 74 97 79 100 95 101 151 104 169 115 180 126 169 129 31 132 19 145 16 153 20 158 32 162 142 166 149 177 149 185 137 185 119 189 108 199 103 212 108 215 121 215 144 210 165 196 177 176 181 164 182 159 302\n" + 	   
                "Line -16777216 false 142 32 146 143\n" + 	   
                "Line -16777216 false 148 179 143 300\n" + 	   
                "Line -16777216 false 123 191 114 197\n" + 	   
                "Line -16777216 false 113 199 96 188\n" + 	   
                "Line -16777216 false 95 188 84 168\n" + 	   
                "Line -16777216 false 83 168 82 103\n" + 	   
                "Line -16777216 false 201 147 202 123\n" + 	   
                "Line -16777216 false 190 162 199 148\n" + 	   
                "Line -16777216 false 174 164 189 163\n" + 	   
                "\n" + 	   
                "campsite\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 11 30 221 270 221\n" + 	   
                "Polygon -16777216 true false 151 90 92 221 212 221\n" + 	   
                "Line -7500403 true 150 30 150 225\n" + 	   
                "\n" + 	   
                "cannon\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 165 0 165 15 180 150 195 165 195 180 180 195 165 225 135 225 120 195 105 180 105 165 120 150 135 15 135 0\n" + 	   
                "Line -16777216 false 120 150 180 150\n" + 	   
                "Line -16777216 false 120 195 180 195\n" + 	   
                "Line -16777216 false 165 15 135 15\n" + 	   
                "Polygon -16777216 false false 165 0 135 0 135 15 120 150 105 165 105 180 120 195 135 225 165 225 180 195 195 180 195 165 180 150 165 15\n" + 	   
                "\n" + 	   
                "car\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180\n" + 	   
                "Circle -16777216 true false 180 180 90\n" + 	   
                "Circle -16777216 true false 30 180 90\n" + 	   
                "Polygon -16777216 true false 162 80 132 78 134 135 209 135 194 105 189 96 180 89\n" + 	   
                "Circle -7500403 true true 47 195 58\n" + 	   
                "Circle -7500403 true true 195 195 58\n" + 	   
                "\n" + 	   
                "car side\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 19 147 11 125 16 105 63 105 99 79 155 79 180 105 243 111 266 129 253 149\n" + 	   
                "Circle -16777216 true false 43 123 42\n" + 	   
                "Circle -16777216 true false 194 124 42\n" + 	   
                "Polygon -16777216 true false 101 87 73 108 171 108 151 87\n" + 	   
                "Line -8630108 false 121 82 120 108\n" + 	   
                "Polygon -1 true false 242 121 248 128 266 129 247 115\n" + 	   
                "Rectangle -16777216 true false 12 131 28 143\n" + 	   
                "\n" + 	   
                "cat\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Line -7500403 true 285 240 210 240\n" + 	   
                "Line -7500403 true 195 300 165 255\n" + 	   
                "Line -7500403 true 15 240 90 240\n" + 	   
                "Line -7500403 true 285 285 195 240\n" + 	   
                "Line -7500403 true 105 300 135 255\n" + 	   
                "Line -16777216 false 150 270 150 285\n" + 	   
                "Line -16777216 false 15 75 15 120\n" + 	   
                "Polygon -7500403 true true 300 15 285 30 255 30 225 75 195 60 255 15\n" + 	   
                "Polygon -7500403 true true 285 135 210 135 180 150 180 45 285 90\n" + 	   
                "Polygon -7500403 true true 120 45 120 210 180 210 180 45\n" + 	   
                "Polygon -7500403 true true 180 195 165 300 240 285 255 225 285 195\n" + 	   
                "Polygon -7500403 true true 180 225 195 285 165 300 150 300 150 255 165 225\n" + 	   
                "Polygon -7500403 true true 195 195 195 165 225 150 255 135 285 135 285 195\n" + 	   
                "Polygon -7500403 true true 15 135 90 135 120 150 120 45 15 90\n" + 	   
                "Polygon -7500403 true true 120 195 135 300 60 285 45 225 15 195\n" + 	   
                "Polygon -7500403 true true 120 225 105 285 135 300 150 300 150 255 135 225\n" + 	   
                "Polygon -7500403 true true 105 195 105 165 75 150 45 135 15 135 15 195\n" + 	   
                "Polygon -7500403 true true 285 120 270 90 285 15 300 15\n" + 	   
                "Line -7500403 true 15 285 105 240\n" + 	   
                "Polygon -7500403 true true 15 120 30 90 15 15 0 15\n" + 	   
                "Polygon -7500403 true true 0 15 15 30 45 30 75 75 105 60 45 15\n" + 	   
                "Line -16777216 false 164 262 209 262\n" + 	   
                "Line -16777216 false 223 231 208 261\n" + 	   
                "Line -16777216 false 136 262 91 262\n" + 	   
                "Line -16777216 false 77 231 92 261\n" + 	   
                "\n" + 	   
                "caterpillar\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 165 210 165 225 135 255 105 270 90 270 75 255 75 240 90 210 120 195 135 165 165 135 165 105 150 75 150 60 135 60 120 45 120 30 135 15 150 15 180 30 180 45 195 45 210 60 225 105 225 135 210 150 210 165 195 195 180 210\n" + 	   
                "Line -16777216 false 135 255 90 210\n" + 	   
                "Line -16777216 false 165 225 120 195\n" + 	   
                "Line -16777216 false 135 165 180 210\n" + 	   
                "Line -16777216 false 150 150 201 186\n" + 	   
                "Line -16777216 false 165 135 210 150\n" + 	   
                "Line -16777216 false 165 120 225 120\n" + 	   
                "Line -16777216 false 165 106 221 90\n" + 	   
                "Line -16777216 false 157 91 210 60\n" + 	   
                "Line -16777216 false 150 60 180 45\n" + 	   
                "Line -16777216 false 120 30 96 26\n" + 	   
                "Line -16777216 false 124 0 135 15\n" + 	   
                "\n" + 	   
                "chess bishop\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 135 35 30\n" + 	   
                "Circle -16777216 false false 135 35 30\n" + 	   
                "Rectangle -7500403 true true 90 255 210 300\n" + 	   
                "Line -16777216 false 75 255 225 255\n" + 	   
                "Rectangle -16777216 false false 90 255 210 300\n" + 	   
                "Polygon -7500403 true true 105 255 120 165 180 165 195 255\n" + 	   
                "Polygon -16777216 false false 105 255 120 165 180 165 195 255\n" + 	   
                "Rectangle -7500403 true true 105 165 195 150\n" + 	   
                "Rectangle -16777216 false false 105 150 195 165\n" + 	   
                "Line -16777216 false 137 59 162 59\n" + 	   
                "Polygon -7500403 true true 135 60 120 75 120 105 120 120 105 120 105 90 90 105 90 120 90 135 105 150 195 150 210 135 210 120 210 105 195 90 165 60\n" + 	   
                "Polygon -16777216 false false 135 60 120 75 120 120 105 120 105 90 90 105 90 135 105 150 195 150 210 135 210 105 165 60\n" + 	   
                "\n" + 	   
                "chess king\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 105 255 120 90 180 90 195 255\n" + 	   
                "Polygon -16777216 false false 105 255 120 90 180 90 195 255\n" + 	   
                "Polygon -7500403 true true 120 85 105 40 195 40 180 85\n" + 	   
                "Polygon -16777216 false false 119 85 104 40 194 40 179 85\n" + 	   
                "Rectangle -7500403 true true 105 105 195 75\n" + 	   
                "Rectangle -16777216 false false 105 75 195 105\n" + 	   
                "Rectangle -7500403 true true 90 255 210 300\n" + 	   
                "Line -16777216 false 75 255 225 255\n" + 	   
                "Rectangle -16777216 false false 90 255 210 300\n" + 	   
                "Rectangle -7500403 true true 165 23 134 13\n" + 	   
                "Rectangle -7500403 true true 144 0 154 44\n" + 	   
                "Polygon -16777216 false false 153 0 144 0 144 13 133 13 133 22 144 22 144 41 154 41 154 22 165 22 165 12 153 12\n" + 	   
                "\n" + 	   
                "chess knight\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Line -16777216 false 75 255 225 255\n" + 	   
                "Polygon -7500403 true true 90 255 60 255 60 225 75 180 75 165 60 135 45 90 60 75 60 45 90 30 120 30 135 45 240 60 255 75 255 90 255 105 240 120 225 105 180 120 210 150 225 195 225 210 210 255\n" + 	   
                "Polygon -16777216 false false 210 255 60 255 60 225 75 180 75 165 60 135 45 90 60 75 60 45 90 30 120 30 135 45 240 60 255 75 255 90 255 105 240 120 225 105 180 120 210 150 225 195 225 210\n" + 	   
                "Line -16777216 false 255 90 240 90\n" + 	   
                "Circle -16777216 true false 134 63 24\n" + 	   
                "Line -16777216 false 103 34 108 45\n" + 	   
                "Line -16777216 false 80 41 88 49\n" + 	   
                "Line -16777216 false 61 53 70 58\n" + 	   
                "Line -16777216 false 64 75 79 75\n" + 	   
                "Line -16777216 false 53 100 67 98\n" + 	   
                "Line -16777216 false 63 126 69 123\n" + 	   
                "Line -16777216 false 71 148 77 145\n" + 	   
                "Rectangle -7500403 true true 90 255 210 300\n" + 	   
                "Rectangle -16777216 false false 90 255 210 300\n" + 	   
                "\n" + 	   
                "chess pawn\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 105 65 90\n" + 	   
                "Circle -16777216 false false 105 65 90\n" + 	   
                "Rectangle -7500403 true true 90 255 210 300\n" + 	   
                "Line -16777216 false 75 255 225 255\n" + 	   
                "Rectangle -16777216 false false 90 255 210 300\n" + 	   
                "Polygon -7500403 true true 105 255 120 165 180 165 195 255\n" + 	   
                "Polygon -16777216 false false 105 255 120 165 180 165 195 255\n" + 	   
                "Rectangle -7500403 true true 105 165 195 150\n" + 	   
                "Rectangle -16777216 false false 105 150 195 165\n" + 	   
                "\n" + 	   
                "chess queen\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 140 11 20\n" + 	   
                "Circle -16777216 false false 139 11 20\n" + 	   
                "Circle -7500403 true true 120 22 60\n" + 	   
                "Circle -16777216 false false 119 20 60\n" + 	   
                "Rectangle -7500403 true true 90 255 210 300\n" + 	   
                "Line -16777216 false 75 255 225 255\n" + 	   
                "Rectangle -16777216 false false 90 255 210 300\n" + 	   
                "Polygon -7500403 true true 105 255 120 90 180 90 195 255\n" + 	   
                "Polygon -16777216 false false 105 255 120 90 180 90 195 255\n" + 	   
                "Rectangle -7500403 true true 105 105 195 75\n" + 	   
                "Rectangle -16777216 false false 105 75 195 105\n" + 	   
                "Polygon -7500403 true true 120 75 105 45 195 45 180 75\n" + 	   
                "Polygon -16777216 false false 120 75 105 45 195 45 180 75\n" + 	   
                "Circle -7500403 true true 180 35 20\n" + 	   
                "Circle -16777216 false false 180 35 20\n" + 	   
                "Circle -7500403 true true 140 35 20\n" + 	   
                "Circle -16777216 false false 140 35 20\n" + 	   
                "Circle -7500403 true true 100 35 20\n" + 	   
                "Circle -16777216 false false 99 35 20\n" + 	   
                "Line -16777216 false 105 90 195 90\n" + 	   
                "\n" + 	   
                "chess rook\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 90 255 210 300\n" + 	   
                "Line -16777216 false 75 255 225 255\n" + 	   
                "Rectangle -16777216 false false 90 255 210 300\n" + 	   
                "Polygon -7500403 true true 90 255 105 105 195 105 210 255\n" + 	   
                "Polygon -16777216 false false 90 255 105 105 195 105 210 255\n" + 	   
                "Rectangle -7500403 true true 75 90 120 60\n" + 	   
                "Rectangle -7500403 true true 75 84 225 105\n" + 	   
                "Rectangle -7500403 true true 135 90 165 60\n" + 	   
                "Rectangle -7500403 true true 180 90 225 60\n" + 	   
                "Polygon -16777216 false false 90 105 75 105 75 60 120 60 120 84 135 84 135 60 165 60 165 84 179 84 180 60 225 60 225 105\n" + 	   
                "\n" + 	   
                "circle\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 0 0 300\n" + 	   
                "\n" + 	   
                "circle 2\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 0 0 300\n" + 	   
                "Circle -16777216 true false 30 30 240\n" + 	   
                "\n" + 	   
                "clock\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 30 30 240\n" + 	   
                "Polygon -16777216 true false 150 31 128 75 143 75 143 150 158 150 158 75 173 75\n" + 	   
                "Circle -16777216 true false 135 135 30\n" + 	   
                "\n" + 	   
                "cloud\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 13 118 94\n" + 	   
                "Circle -7500403 true true 86 101 127\n" + 	   
                "Circle -7500403 true true 51 51 108\n" + 	   
                "Circle -7500403 true true 118 43 95\n" + 	   
                "Circle -7500403 true true 158 68 134\n" + 	   
                "\n" + 	   
                "coin heads\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 15 15 270\n" + 	   
                "Circle -16777216 false false 22 21 256\n" + 	   
                "Line -16777216 false 165 180 192 196\n" + 	   
                "Line -16777216 false 42 140 83 140\n" + 	   
                "Line -16777216 false 37 151 91 151\n" + 	   
                "Line -16777216 false 218 167 265 167\n" + 	   
                "Polygon -16777216 false false 148 265 75 229 86 207 113 191 120 175 109 162 109 136 86 124 137 96 176 93 210 108 222 125 203 157 204 174 190 191 232 230\n" + 	   
                "Polygon -16777216 false false 212 142 182 128 154 132 140 152 149 162 144 182 167 204 187 206 193 193 190 189 202 174 193 158 202 175 204 158\n" + 	   
                "Line -16777216 false 164 154 182 152\n" + 	   
                "Line -16777216 false 193 152 202 153\n" + 	   
                "Polygon -16777216 false false 60 75 75 90 90 75 105 75 90 45 105 45 120 60 135 60 135 45 120 45 105 45 135 30 165 30 195 45 210 60 225 75 240 75 225 75 210 90 225 75 225 60 210 60 195 75 210 60 195 45 180 45 180 60 180 45 165 60 150 60 150 45 165 45 150 45 150 30 135 30 120 60 105 75\n" + 	   
                "\n" + 	   
                "coin tails\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 15 15 270\n" + 	   
                "Circle -16777216 false false 20 17 260\n" + 	   
                "Line -16777216 false 130 92 171 92\n" + 	   
                "Line -16777216 false 123 79 177 79\n" + 	   
                "Rectangle -7500403 true true 57 101 242 133\n" + 	   
                "Rectangle -16777216 false false 45 180 255 195\n" + 	   
                "Rectangle -16777216 false false 75 120 225 135\n" + 	   
                "Polygon -16777216 false false 81 226 70 241 86 248 93 235 89 232 108 243 97 256 118 247 118 265 123 248 142 247 129 253 130 271 145 269 131 259 162 245 153 262 168 268 197 259 177 255 187 245 174 243 193 235 209 251 193 234 225 244 208 227 240 240 222 218\n" + 	   
                "Rectangle -7500403 true true 91 210 222 226\n" + 	   
                "Polygon -16777216 false false 65 70 91 50 136 35 181 35 226 65 246 86 241 65 196 50 166 35 121 50 91 50 61 95 54 80 61 65\n" + 	   
                "Polygon -16777216 false false 90 135 60 135 60 180 90 180 90 135 120 135 120 180 150 180 150 135 180 135 180 180 210 180 210 135 240 135 240 180 210 180 210 135\n" + 	   
                "\n" + 	   
                "computer workstation\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 60 45 240 180\n" + 	   
                "Polygon -7500403 true true 90 180 105 195 135 195 135 210 165 210 165 195 195 195 210 180\n" + 	   
                "Rectangle -16777216 true false 75 60 225 165\n" + 	   
                "Rectangle -7500403 true true 45 210 255 255\n" + 	   
                "Rectangle -10899396 true false 249 223 237 217\n" + 	   
                "Line -16777216 false 60 225 120 225\n" + 	   
                "\n" + 	   
                "cow\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 200 193 197 249 179 249 177 196 166 187 140 189 93 191 78 179 72 211 49 209 48 181 37 149 25 120 25 89 45 72 103 84 179 75 198 76 252 64 272 81 293 103 285 121 255 121 242 118 224 167\n" + 	   
                "Polygon -7500403 true true 73 210 86 251 62 249 48 208\n" + 	   
                "Polygon -7500403 true true 25 114 16 195 9 204 23 213 25 200 39 123\n" + 	   
                "\n" + 	   
                "crate\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 45 45 255 255\n" + 	   
                "Rectangle -16777216 false false 45 45 255 255\n" + 	   
                "Rectangle -16777216 false false 60 60 240 240\n" + 	   
                "Line -16777216 false 180 60 180 240\n" + 	   
                "Line -16777216 false 150 60 150 240\n" + 	   
                "Line -16777216 false 120 60 120 240\n" + 	   
                "Line -16777216 false 210 60 210 240\n" + 	   
                "Line -16777216 false 90 60 90 240\n" + 	   
                "Polygon -7500403 true true 75 240 240 75 240 60 225 60 60 225 60 240\n" + 	   
                "Polygon -16777216 false false 60 225 60 240 75 240 240 75 240 60 225 60\n" + 	   
                "\n" + 	   
                "crown\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 45 165 255 240\n" + 	   
                "Polygon -7500403 true true 45 165 30 60 90 165 90 60 132 166 150 60 169 166 210 60 210 165 270 60 255 165\n" + 	   
                "Circle -16777216 true false 222 192 22\n" + 	   
                "Circle -16777216 true false 56 192 22\n" + 	   
                "Circle -16777216 true false 99 192 22\n" + 	   
                "Circle -16777216 true false 180 192 22\n" + 	   
                "Circle -16777216 true false 139 192 22\n" + 	   
                "\n" + 	   
                "cylinder\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 0 0 300\n" + 	   
                "\n" + 	   
                "dart\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 135 90 150 285 165 90\n" + 	   
                "Polygon -7500403 true true 135 285 105 255 105 240 120 210 135 180 150 165 165 180 180 210 195 240 195 255 165 285\n" + 	   
                "Rectangle -1184463 true false 135 45 165 90\n" + 	   
                "Line -16777216 false 150 285 150 180\n" + 	   
                "Polygon -16777216 true false 150 45 135 45 146 35 150 0 155 35 165 45\n" + 	   
                "Line -16777216 false 135 75 165 75\n" + 	   
                "Line -16777216 false 135 60 165 60\n" + 	   
                "\n" + 	   
                "die 1\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 45 45 255 255\n" + 	   
                "Circle -16777216 true false 129 129 42\n" + 	   
                "\n" + 	   
                "die 2\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 45 45 255 255\n" + 	   
                "Circle -16777216 true false 69 69 42\n" + 	   
                "Circle -16777216 true false 189 189 42\n" + 	   
                "\n" + 	   
                "die 3\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 45 45 255 255\n" + 	   
                "Circle -16777216 true false 69 69 42\n" + 	   
                "Circle -16777216 true false 129 129 42\n" + 	   
                "Circle -16777216 true false 189 189 42\n" + 	   
                "\n" + 	   
                "die 4\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 45 45 255 255\n" + 	   
                "Circle -16777216 true false 69 69 42\n" + 	   
                "Circle -16777216 true false 69 189 42\n" + 	   
                "Circle -16777216 true false 189 69 42\n" + 	   
                "Circle -16777216 true false 189 189 42\n" + 	   
                "\n" + 	   
                "die 5\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 45 45 255 255\n" + 	   
                "Circle -16777216 true false 69 69 42\n" + 	   
                "Circle -16777216 true false 129 129 42\n" + 	   
                "Circle -16777216 true false 69 189 42\n" + 	   
                "Circle -16777216 true false 189 69 42\n" + 	   
                "Circle -16777216 true false 189 189 42\n" + 	   
                "\n" + 	   
                "die 6\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 45 45 255 255\n" + 	   
                "Circle -16777216 true false 84 69 42\n" + 	   
                "Circle -16777216 true false 84 129 42\n" + 	   
                "Circle -16777216 true false 84 189 42\n" + 	   
                "Circle -16777216 true false 174 69 42\n" + 	   
                "Circle -16777216 true false 174 129 42\n" + 	   
                "Circle -16777216 true false 174 189 42\n" + 	   
                "\n" + 	   
                "dog\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 300 165 300 195 270 210 183 204 180 240 165 270 165 300 120 300 0 240 45 165 75 90 75 45 105 15 135 45 165 45 180 15 225 15 255 30 225 30 210 60 225 90 225 105\n" + 	   
                "Polygon -16777216 true false 0 240 120 300 165 300 165 285 120 285 10 221\n" + 	   
                "Line -16777216 false 210 60 180 45\n" + 	   
                "Line -16777216 false 90 45 90 90\n" + 	   
                "Line -16777216 false 90 90 105 105\n" + 	   
                "Line -16777216 false 105 105 135 60\n" + 	   
                "Line -16777216 false 90 45 135 60\n" + 	   
                "Line -16777216 false 135 60 135 45\n" + 	   
                "Line -16777216 false 181 203 151 203\n" + 	   
                "Line -16777216 false 150 201 105 171\n" + 	   
                "Circle -16777216 true false 171 88 34\n" + 	   
                "Circle -16777216 false false 261 162 30\n" + 	   
                "\n" + 	   
                "dollar bill\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 15 90 285 210\n" + 	   
                "Rectangle -1 true false 30 105 270 195\n" + 	   
                "Circle -7500403 true true 120 120 60\n" + 	   
                "Circle -7500403 true true 120 135 60\n" + 	   
                "Circle -7500403 true true 254 178 26\n" + 	   
                "Circle -7500403 true true 248 98 26\n" + 	   
                "Circle -7500403 true true 18 97 36\n" + 	   
                "Circle -7500403 true true 21 178 26\n" + 	   
                "Circle -7500403 true true 66 135 28\n" + 	   
                "Circle -1 true false 72 141 16\n" + 	   
                "Circle -7500403 true true 201 138 32\n" + 	   
                "Circle -1 true false 209 146 16\n" + 	   
                "Rectangle -16777216 true false 64 112 86 118\n" + 	   
                "Rectangle -16777216 true false 90 112 124 118\n" + 	   
                "Rectangle -16777216 true false 128 112 188 118\n" + 	   
                "Rectangle -16777216 true false 191 112 237 118\n" + 	   
                "Rectangle -1 true false 106 199 128 205\n" + 	   
                "Rectangle -1 true false 90 96 209 98\n" + 	   
                "Rectangle -7500403 true true 60 168 103 176\n" + 	   
                "Rectangle -7500403 true true 199 127 230 133\n" + 	   
                "Line -7500403 true 59 184 104 184\n" + 	   
                "Line -7500403 true 241 189 196 189\n" + 	   
                "Line -7500403 true 59 189 104 189\n" + 	   
                "Line -16777216 false 116 124 71 124\n" + 	   
                "Polygon -1 true false 127 179 142 167 142 160 130 150 126 148 142 132 158 132 173 152 167 156 164 167 174 176 161 193 135 192\n" + 	   
                "Rectangle -1 true false 134 199 184 205\n" + 	   
                "\n" + 	   
                "dot\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 90 90 120\n" + 	   
                "\n" + 	   
                "drop\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 73 133 152\n" + 	   
                "Polygon -7500403 true true 219 181 205 152 185 120 174 95 163 64 156 37 149 7 147 166\n" + 	   
                "Polygon -7500403 true true 79 182 95 152 115 120 126 95 137 64 144 37 150 6 154 165\n" + 	   
                "\n" + 	   
                "egg\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 96 76 108\n" + 	   
                "Circle -7500403 true true 72 104 156\n" + 	   
                "Polygon -7500403 true true 221 149 195 101 106 99 80 148\n" + 	   
                "\n" + 	   
                "exclamation\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 103 198 95\n" + 	   
                "Polygon -7500403 true true 135 180 165 180 210 30 180 0 120 0 90 30\n" + 	   
                "\n" + 	   
                "eyeball\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -1 true false 22 20 248\n" + 	   
                "Circle -7500403 true true 83 81 122\n" + 	   
                "Circle -16777216 true false 122 120 44\n" + 	   
                "\n" + 	   
                "face happy\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 8 8 285\n" + 	   
                "Circle -16777216 true false 60 75 60\n" + 	   
                "Circle -16777216 true false 180 75 60\n" + 	   
                "Polygon -16777216 true false 150 255 90 239 62 213 47 191 67 179 90 203 109 218 150 225 192 218 210 203 227 181 251 194 236 217 212 240\n" + 	   
                "\n" + 	   
                "face neutral\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 8 7 285\n" + 	   
                "Circle -16777216 true false 60 75 60\n" + 	   
                "Circle -16777216 true false 180 75 60\n" + 	   
                "Rectangle -16777216 true false 60 195 240 225\n" + 	   
                "\n" + 	   
                "face sad\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 8 8 285\n" + 	   
                "Circle -16777216 true false 60 75 60\n" + 	   
                "Circle -16777216 true false 180 75 60\n" + 	   
                "Polygon -16777216 true false 150 168 90 184 62 210 47 232 67 244 90 220 109 205 150 198 192 205 210 220 227 242 251 229 236 206 212 183\n" + 	   
                "\n" + 	   
                "factory\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 76 194 285 270\n" + 	   
                "Rectangle -7500403 true true 36 95 59 231\n" + 	   
                "Rectangle -16777216 true false 90 210 270 240\n" + 	   
                "Line -7500403 true 90 195 90 255\n" + 	   
                "Line -7500403 true 120 195 120 255\n" + 	   
                "Line -7500403 true 150 195 150 240\n" + 	   
                "Line -7500403 true 180 195 180 255\n" + 	   
                "Line -7500403 true 210 210 210 240\n" + 	   
                "Line -7500403 true 240 210 240 240\n" + 	   
                "Line -7500403 true 90 225 270 225\n" + 	   
                "Circle -1 true false 37 73 32\n" + 	   
                "Circle -1 true false 55 38 54\n" + 	   
                "Circle -1 true false 96 21 42\n" + 	   
                "Circle -1 true false 105 40 32\n" + 	   
                "Circle -1 true false 129 19 42\n" + 	   
                "Rectangle -7500403 true true 14 228 78 270\n" + 	   
                "\n" + 	   
                "fire\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 151 286 134 282 103 282 59 248 40 210 32 157 37 108 68 146 71 109 83 72 111 27 127 55 148 11 167 41 180 112 195 57 217 91 226 126 227 203 256 156 256 201 238 263 213 278 183 281\n" + 	   
                "Polygon -955883 true false 126 284 91 251 85 212 91 168 103 132 118 153 125 181 135 141 151 96 185 161 195 203 193 253 164 286\n" + 	   
                "Polygon -2674135 true false 155 284 172 268 172 243 162 224 148 201 130 233 131 260 135 282\n" + 	   
                "\n" + 	   
                "fish\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166\n" + 	   
                "Polygon -1 true false 135 195 119 235 95 218 76 210 46 204 60 165\n" + 	   
                "Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60\n" + 	   
                "Polygon -7500403 true true 30 136 151 77 226 81 280 119 292 146 292 160 287 170 270 195 195 210 151 212 30 166\n" + 	   
                "Circle -16777216 true false 215 106 30\n" + 	   
                "\n" + 	   
                "flag\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 60 15 75 300\n" + 	   
                "Polygon -7500403 true true 90 150 270 90 90 30\n" + 	   
                "Line -7500403 true 75 135 90 135\n" + 	   
                "Line -7500403 true 75 45 90 45\n" + 	   
                "\n" + 	   
                "flower\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -10899396 true false 135 120 165 165 180 210 180 240 150 300 165 300 195 240 195 195 165 135\n" + 	   
                "Circle -7500403 true true 85 132 38\n" + 	   
                "Circle -7500403 true true 130 147 38\n" + 	   
                "Circle -7500403 true true 192 85 38\n" + 	   
                "Circle -7500403 true true 85 40 38\n" + 	   
                "Circle -7500403 true true 177 40 38\n" + 	   
                "Circle -7500403 true true 177 132 38\n" + 	   
                "Circle -7500403 true true 70 85 38\n" + 	   
                "Circle -7500403 true true 130 25 38\n" + 	   
                "Circle -7500403 true true 96 51 108\n" + 	   
                "Circle -16777216 true false 113 68 74\n" + 	   
                "Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218\n" + 	   
                "Polygon -10899396 true false 180 255 150 210 105 210 75 240 135 240\n" + 	   
                "\n"; 	   
                
        return formas;
    }
    
    private String declaracaoFormas2()
    {
        String formas = 
                "footprint human\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 111 244 115 272 130 286 151 288 168 277 176 257 177 234 175 195 174 172 170 135 177 104 188 79 188 55 179 45 181 32 185 17 176 1 159 2 154 17 161 32 158 44 146 47 144 35 145 21 135 7 124 9 120 23 129 36 133 49 121 47 100 56 89 73 73 94 74 121 86 140 99 163 110 191\n" + 	   
                "Polygon -7500403 true true 97 37 101 44 111 43 118 35 111 23 100 20 95 25\n" + 	   
                "Polygon -7500403 true true 77 52 81 59 91 58 96 50 88 39 82 37 76 42\n" + 	   
                "Polygon -7500403 true true 63 72 67 79 77 78 79 70 73 63 68 60 63 65\n" + 	   
                "\n" + 	   
                "frog top\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 146 18 135 30 119 42 105 90 90 150 105 195 135 225 165 225 195 195 210 150 195 90 180 41 165 30 155 18\n" + 	   
                "Polygon -7500403 true true 91 176 67 148 70 121 66 119 61 133 59 111 53 111 52 131 47 115 42 120 46 146 55 187 80 237 106 269 116 268 114 214 131 222\n" + 	   
                "Polygon -7500403 true true 185 62 234 84 223 51 226 48 234 61 235 38 240 38 243 60 252 46 255 49 244 95 188 92\n" + 	   
                "Polygon -7500403 true true 115 62 66 84 77 51 74 48 66 61 65 38 60 38 57 60 48 46 45 49 56 95 112 92\n" + 	   
                "Polygon -7500403 true true 200 186 233 148 230 121 234 119 239 133 241 111 247 111 248 131 253 115 258 120 254 146 245 187 220 237 194 269 184 268 186 214 169 222\n" + 	   
                "Circle -16777216 true false 157 38 18\n" + 	   
                "Circle -16777216 true false 125 38 18\n" + 	   
                "\n" + 	   
                "garbage can\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -16777216 false false 60 240 66 257 90 285 134 299 164 299 209 284 234 259 240 240\n" + 	   
                "Rectangle -7500403 true true 60 75 240 240\n" + 	   
                "Polygon -7500403 true true 60 238 66 256 90 283 135 298 165 298 210 283 235 256 240 238\n" + 	   
                "Polygon -7500403 true true 60 75 66 57 90 30 135 15 165 15 210 30 235 57 240 75\n" + 	   
                "Polygon -7500403 true true 60 75 66 93 90 120 135 135 165 135 210 120 235 93 240 75\n" + 	   
                "Polygon -16777216 false false 59 75 66 57 89 30 134 15 164 15 209 30 234 56 239 75 235 91 209 120 164 135 134 135 89 120 64 90\n" + 	   
                "Line -16777216 false 210 120 210 285\n" + 	   
                "Line -16777216 false 90 120 90 285\n" + 	   
                "Line -16777216 false 125 131 125 296\n" + 	   
                "Line -16777216 false 65 93 65 258\n" + 	   
                "Line -16777216 false 175 131 175 296\n" + 	   
                "Line -16777216 false 235 93 235 258\n" + 	   
                "Polygon -16777216 false false 112 52 112 66 127 51 162 64 170 87 185 85 192 71 180 54 155 39 127 36\n" + 	   
                "\n" + 	   
                "ghost\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 30 165 13 164 -2 149 0 135 -2 119 0 105 15 75 30 75 58 104 43 119 43 134 58 134 73 134 88 104 73 44 78 14 103 -1 193 -1 223 29 208 89 208 119 238 134 253 119 240 105 238 89 240 75 255 60 270 60 283 74 300 90 298 104 298 119 300 135 285 135 285 150 268 164 238 179 208 164 208 194 238 209 253 224 268 239 268 269 238 299 178 299 148 284 103 269 58 284 43 299 58 269 103 254 148 254 193 254 163 239 118 209 88 179 73 179 58 164\n" + 	   
                "Line -16777216 false 189 253 215 253\n" + 	   
                "Circle -16777216 true false 102 30 30\n" + 	   
                "Polygon -16777216 true false 165 105 135 105 120 120 105 105 135 75 165 75 195 105 180 120\n" + 	   
                "Circle -16777216 true false 160 30 30\n" + 	   
                "\n" + 	   
                "hawk\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 151 170 136 170 123 229 143 244 156 244 179 229 166 170\n" + 	   
                "Polygon -16777216 true false 152 154 137 154 125 213 140 229 159 229 179 214 167 154\n" + 	   
                "Polygon -7500403 true true 151 140 136 140 126 202 139 214 159 214 176 200 166 140\n" + 	   
                "Polygon -16777216 true false 151 125 134 124 128 188 140 198 161 197 174 188 166 125\n" + 	   
                "Polygon -7500403 true true 152 86 227 72 286 97 272 101 294 117 276 118 287 131 270 131 278 141 264 138 267 145 228 150 153 147\n" + 	   
                "Polygon -7500403 true true 160 74 159 61 149 54 130 53 139 62 133 81 127 113 129 149 134 177 150 206 168 179 172 147 169 111\n" + 	   
                "Circle -16777216 true false 144 55 7\n" + 	   
                "Polygon -16777216 true false 129 53 135 58 139 54\n" + 	   
                "Polygon -7500403 true true 148 86 73 72 14 97 28 101 6 117 24 118 13 131 30 131 22 141 36 138 33 145 72 150 147 147\n" + 	   
                "\n" + 	   
                "hex\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 0 150 75 30 225 30 300 150 225 270 75 270\n" + 	   
                "\n" + 	   
                "house\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 45 120 255 285\n" + 	   
                "Rectangle -16777216 true false 120 210 180 285\n" + 	   
                "Polygon -7500403 true true 15 120 150 15 285 120\n" + 	   
                "Line -16777216 false 30 120 270 120\n" + 	   
                "\n" + 	   
                "house bungalow\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 210 75 225 255\n" + 	   
                "Rectangle -7500403 true true 90 135 210 255\n" + 	   
                "Rectangle -16777216 true false 165 195 195 255\n" + 	   
                "Line -16777216 false 210 135 210 255\n" + 	   
                "Rectangle -16777216 true false 105 202 135 240\n" + 	   
                "Polygon -7500403 true true 225 150 75 150 150 75\n" + 	   
                "Line -16777216 false 75 150 225 150\n" + 	   
                "Line -16777216 false 195 120 225 150\n" + 	   
                "Polygon -16777216 false false 165 195 150 195 180 165 210 195\n" + 	   
                "Rectangle -16777216 true false 135 105 165 135\n" + 	   
                "\n" + 	   
                "house ranch\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 270 120 285 255\n" + 	   
                "Rectangle -7500403 true true 15 180 270 255\n" + 	   
                "Polygon -7500403 true true 0 180 300 180 240 135 60 135 0 180\n" + 	   
                "Rectangle -16777216 true false 120 195 180 255\n" + 	   
                "Line -7500403 true 150 195 150 255\n" + 	   
                "Rectangle -16777216 true false 45 195 105 240\n" + 	   
                "Rectangle -16777216 true false 195 195 255 240\n" + 	   
                "Line -7500403 true 75 195 75 240\n" + 	   
                "Line -7500403 true 225 195 225 240\n" + 	   
                "Line -16777216 false 270 180 270 255\n" + 	   
                "Line -16777216 false 0 180 300 180\n" + 	   
                "\n" + 	   
                "key\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 90 120 285 150\n" + 	   
                "Rectangle -7500403 true true 255 135 285 195\n" + 	   
                "Rectangle -7500403 true true 180 135 210 195\n" + 	   
                "Circle -7500403 true true 0 60 150\n" + 	   
                "Circle -16777216 true false 30 90 90\n" + 	   
                "\n" + 	   
                "leaf\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195\n" + 	   
                "Polygon -7500403 true true 135 195 135 240 120 255 105 255 105 285 135 285 165 240 165 195\n" + 	   
                "\n" + 	   
                "letter sealed\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 30 90 270 225\n" + 	   
                "Rectangle -16777216 false false 30 90 270 225\n" + 	   
                "Line -16777216 false 270 105 150 180\n" + 	   
                "Line -16777216 false 30 105 150 180\n" + 	   
                "Line -16777216 false 270 225 181 161\n" + 	   
                "Line -16777216 false 30 225 119 161\n" + 	   
                "\n" + 	   
                "lightning\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 120 135 90 195 135 195 105 300 225 165 180 165 210 105 165 105 195 0 75 135\n" + 	   
                "\n" + 	   
                "line\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Line -7500403 true 150 0 150 300\n" + 	   
                "\n" + 	   
                "line half\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Line -7500403 true 150 0 150 150\n" + 	   
                "\n" + 	   
                "magnet\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 120 270 75 270 60 105 60 60 75 30 106 10 150 3 195 10 225 30 240 60 240 105 225 270 180 270 195 105 196 74 184 55 165 45 135 45 115 55 104 75 105 105\n" + 	   
                "Polygon -16777216 true false 219 264 188 264 193 214 224 215\n" + 	   
                "Polygon -16777216 true false 81 264 112 264 107 214 76 215\n" + 	   
                "\n" + 	   
                "monster\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 75 150 90 195 210 195 225 150 255 120 255 45 180 0 120 0 45 45 45 120\n" + 	   
                "Circle -16777216 true false 165 60 60\n" + 	   
                "Circle -16777216 true false 75 60 60\n" + 	   
                "Polygon -7500403 true true 225 150 285 195 285 285 255 300 255 210 180 165\n" + 	   
                "Polygon -7500403 true true 75 150 15 195 15 285 45 300 45 210 120 165\n" + 	   
                "Polygon -7500403 true true 210 210 225 285 195 285 165 165\n" + 	   
                "Polygon -7500403 true true 90 210 75 285 105 285 135 165\n" + 	   
                "Rectangle -7500403 true true 135 165 165 270\n" + 	   
                "\n" + 	   
                "moon\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 175 7 83 36 25 108 27 186 79 250 134 271 205 274 281 239 207 233 152 216 113 185 104 132 110 77 132 51\n" + 	   
                "\n" + 	   
                "mouse side\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 38 162 24 165 19 174 22 192 47 213 90 225 135 230 161 240 178 262 150 246 117 238 73 232 36 220 11 196 7 171 15 153 37 146 46 145\n" + 	   
                "Polygon -7500403 true true 289 142 271 165 237 164 217 185 235 192 254 192 259 199 245 200 248 203 226 199 200 194 155 195 122 185 84 187 91 195 82 192 83 201 72 190 67 199 62 185 46 183 36 165 40 134 57 115 74 106 60 109 90 97 112 94 92 93 130 86 154 88 134 81 183 90 197 94 183 86 212 95 211 88 224 83 235 88 248 97 246 90 257 107 255 97 270 120\n" + 	   
                "Polygon -16777216 true false 234 100 220 96 210 100 214 111 228 116 239 115\n" + 	   
                "Circle -16777216 true false 246 117 20\n" + 	   
                "Line -7500403 true 270 153 282 174\n" + 	   
                "Line -7500403 true 272 153 255 173\n" + 	   
                "Line -7500403 true 269 156 268 177\n" + 	   
                "\n" + 	   
                "music notes 1\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 75 210 96 212 118 218 131 229 135 238 135 243 131 251 118 261 96 268 75 270 55 268 33 262 19 251 15 242 15 238 19 229 33 218 54 212\n" + 	   
                "Rectangle -7500403 true true 120 90 135 255\n" + 	   
                "Polygon -7500403 true true 225 165 246 167 268 173 281 184 285 193 285 198 281 206 268 216 246 223 225 225 205 223 183 217 169 206 165 197 165 193 169 184 183 173 204 167\n" + 	   
                "Polygon -7500403 true true 120 60 120 105 285 45 285 0\n" + 	   
                "Rectangle -7500403 true true 270 15 285 195\n" + 	   
                "\n" + 	   
                "music notes 3\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 135 195 156 197 178 203 191 214 195 223 195 228 191 236 178 246 156 253 135 255 115 253 93 247 79 236 75 227 75 223 79 214 93 203 114 197\n" + 	   
                "Rectangle -7500403 true true 180 30 195 225\n" + 	   
                "Polygon -7500403 true true 194 66 210 80 242 93 271 94 293 84 301 68 269 69 238 60 213 46 197 34 193 30\n" + 	   
                "\n" + 	   
                "paintbrush\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -1 true false 87 191 103 218 238 53 223 38\n" + 	   
                "Polygon -13345367 true false 104 204 104 218 239 53 235 47\n" + 	   
                "Polygon -7500403 true true 99 173 83 175 71 186 64 207 52 235 45 251 77 238 108 227 124 205 118 185\n" + 	   
                "\n" + 	   
                "pencil\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 255 60 255 90 105 240 90 225\n" + 	   
                "Polygon -7500403 true true 60 195 75 210 240 45 210 45\n" + 	   
                "Polygon -7500403 true true 90 195 105 210 255 60 240 45\n" + 	   
                "Polygon -6459832 true false 90 195 60 195 45 255 105 240 105 210\n" + 	   
                "Polygon -16777216 true false 45 255 74 248 75 240 60 225 51 225\n" + 	   
                "\n" + 	   
                "pentagon\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120\n" + 	   
                "\n" + 	   
                "person\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90\n" + 	   
                "Rectangle -7500403 true true 127 79 172 94\n" + 	   
                "Polygon -7500403 true true 195 90 240 150 225 180 165 105\n" + 	   
                "Polygon -7500403 true true 105 90 60 150 75 180 135 105\n" + 	   
                "\n" + 	   
                "person business\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -1 true false 120 90 180 180\n" + 	   
                "Polygon -13345367 true false 135 90 150 105 135 180 150 195 165 180 150 105 165 90\n" + 	   
                "Polygon -7500403 true true 120 90 105 90 60 195 90 210 116 154 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 183 153 210 210 240 195 195 90 180 90 150 165\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Rectangle -7500403 true true 127 76 172 91\n" + 	   
                "Line -16777216 false 172 90 161 94\n" + 	   
                "Line -16777216 false 128 90 139 94\n" + 	   
                "Polygon -13345367 true false 195 225 195 300 270 270 270 195\n" + 	   
                "Rectangle -13791810 true false 180 225 195 300\n" + 	   
                "Polygon -14835848 true false 180 226 195 226 270 196 255 196\n" + 	   
                "Polygon -13345367 true false 209 202 209 216 244 202 243 188\n" + 	   
                "Line -16777216 false 180 90 150 165\n" + 	   
                "Line -16777216 false 120 90 150 165\n" + 	   
                "\n" + 	   
                "person construction\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 123 76 176 95\n" + 	   
                "Polygon -1 true false 105 90 60 195 90 210 115 162 184 163 210 210 240 195 195 90\n" + 	   
                "Polygon -13345367 true false 180 195 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Line -16777216 false 148 143 150 196\n" + 	   
                "Rectangle -16777216 true false 116 186 182 198\n" + 	   
                "Circle -1 true false 152 143 9\n" + 	   
                "Circle -1 true false 152 166 9\n" + 	   
                "Rectangle -16777216 true false 179 164 183 186\n" + 	   
                "Polygon -955883 true false 180 90 195 90 195 165 195 195 150 195 150 120 180 90\n" + 	   
                "Polygon -955883 true false 120 90 105 90 105 165 105 195 150 195 150 120 120 90\n" + 	   
                "Rectangle -16777216 true false 135 114 150 120\n" + 	   
                "Rectangle -16777216 true false 135 144 150 150\n" + 	   
                "Rectangle -16777216 true false 135 174 150 180\n" + 	   
                "Polygon -955883 true false 105 42 111 16 128 2 149 0 178 6 190 18 192 28 220 29 216 34 201 39 167 35\n" + 	   
                "Polygon -6459832 true false 54 253 54 238 219 73 227 78\n" + 	   
                "Polygon -16777216 true false 15 285 15 255 30 225 45 225 75 255 75 270 45 285\n" + 	   
                "\n" + 	   
                "person doctor\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90\n" + 	   
                "Polygon -13345367 true false 135 90 150 105 135 135 150 150 165 135 150 105 165 90\n" + 	   
                "Polygon -7500403 true true 105 90 60 195 90 210 135 105\n" + 	   
                "Polygon -7500403 true true 195 90 240 195 210 210 165 105\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Rectangle -7500403 true true 127 79 172 94\n" + 	   
                "Polygon -1 true false 105 90 60 195 90 210 114 156 120 195 90 270 210 270 180 195 186 155 210 210 240 195 195 90 165 90 150 150 135 90\n" + 	   
                "Line -16777216 false 150 148 150 270\n" + 	   
                "Line -16777216 false 196 90 151 149\n" + 	   
                "Line -16777216 false 104 90 149 149\n" + 	   
                "Circle -1 true false 180 0 30\n" + 	   
                "Line -16777216 false 180 15 120 15\n" + 	   
                "Line -16777216 false 150 195 165 195\n" + 	   
                "Line -16777216 false 150 240 165 240\n" + 	   
                "Line -16777216 false 150 150 165 150\n" + 	   
                "\n" + 	   
                "person farmer\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90\n" + 	   
                "Polygon -1 true false 60 195 90 210 114 154 120 195 180 195 187 157 210 210 240 195 195 90 165 90 150 105 150 150 135 90 105 90\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Rectangle -7500403 true true 127 79 172 94\n" + 	   
                "Polygon -13345367 true false 120 90 120 180 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 180 90 172 89 165 135 135 135 127 90\n" + 	   
                "Polygon -6459832 true false 116 4 113 21 71 33 71 40 109 48 117 34 144 27 180 26 188 36 224 23 222 14 178 16 167 0\n" + 	   
                "Line -16777216 false 225 90 270 90\n" + 	   
                "Line -16777216 false 225 15 225 90\n" + 	   
                "Line -16777216 false 270 15 270 90\n" + 	   
                "Line -16777216 false 247 15 247 90\n" + 	   
                "Rectangle -6459832 true false 240 90 255 300\n" + 	   
                "\n" + 	   
                "person graduate\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -16777216 false false 39 183 20\n" + 	   
                "Polygon -1 true false 50 203 85 213 118 227 119 207 89 204 52 185\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Rectangle -7500403 true true 127 79 172 94\n" + 	   
                "Polygon -8630108 true false 90 19 150 37 210 19 195 4 105 4\n" + 	   
                "Polygon -8630108 true false 120 90 105 90 60 195 90 210 120 165 90 285 105 300 195 300 210 285 180 165 210 210 240 195 195 90\n" + 	   
                "Polygon -1184463 true false 135 90 120 90 150 135 180 90 165 90 150 105\n" + 	   
                "Line -2674135 false 195 90 150 135\n" + 	   
                "Line -2674135 false 105 90 150 135\n" + 	   
                "Polygon -1 true false 135 90 150 105 165 90\n" + 	   
                "Circle -1 true false 104 205 20\n" + 	   
                "Circle -1 true false 41 184 20\n" + 	   
                "Circle -16777216 false false 106 206 18\n" + 	   
                "Line -2674135 false 208 22 208 57\n" + 	   
                "\n" + 	   
                "person lumberjack\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90\n" + 	   
                "Polygon -2674135 true false 60 196 90 211 114 155 120 196 180 196 187 158 210 211 240 196 195 91 165 91 150 106 150 135 135 91 105 91\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Rectangle -7500403 true true 127 79 172 94\n" + 	   
                "Polygon -6459832 true false 174 90 181 90 180 195 165 195\n" + 	   
                "Polygon -13345367 true false 180 195 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285\n" + 	   
                "Polygon -6459832 true false 126 90 119 90 120 195 135 195\n" + 	   
                "Rectangle -6459832 true false 45 180 255 195\n" + 	   
                "Polygon -16777216 true false 255 165 255 195 240 225 255 240 285 240 300 225 285 195 285 165\n" + 	   
                "Line -16777216 false 135 165 165 165\n" + 	   
                "Line -16777216 false 135 135 165 135\n" + 	   
                "Line -16777216 false 90 135 120 135\n" + 	   
                "Line -16777216 false 105 120 120 120\n" + 	   
                "Line -16777216 false 180 120 195 120\n" + 	   
                "Line -16777216 false 180 135 210 135\n" + 	   
                "Line -16777216 false 90 150 105 165\n" + 	   
                "Line -16777216 false 225 165 210 180\n" + 	   
                "Line -16777216 false 75 165 90 180\n" + 	   
                "Line -16777216 false 210 150 195 165\n" + 	   
                "Line -16777216 false 180 105 210 180\n" + 	   
                "Line -16777216 false 120 105 90 180\n" + 	   
                "Line -16777216 false 150 135 150 165\n" + 	   
                "Polygon -2674135 true false 100 30 104 44 189 24 185 10 173 10 166 1 138 -1 111 3 109 28\n" + 	   
                "\n" + 	   
                "person police\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -1 true false 124 91 150 165 178 91\n" + 	   
                "Polygon -13345367 true false 134 91 149 106 134 181 149 196 164 181 149 106 164 91\n" + 	   
                "Polygon -13345367 true false 180 195 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285\n" + 	   
                "Polygon -13345367 true false 120 90 105 90 60 195 90 210 116 158 120 195 180 195 184 158 210 210 240 195 195 90 180 90 165 105 150 165 135 105 120 90\n" + 	   
                "Rectangle -7500403 true true 123 76 176 92\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Polygon -13345367 true false 150 26 110 41 97 29 137 -1 158 6 185 0 201 6 196 23 204 34 180 33\n" + 	   
                "Line -13345367 false 121 90 194 90\n" + 	   
                "Line -16777216 false 148 143 150 196\n" + 	   
                "Rectangle -16777216 true false 116 186 182 198\n" + 	   
                "Rectangle -16777216 true false 109 183 124 227\n" + 	   
                "Rectangle -16777216 true false 176 183 195 205\n" + 	   
                "Circle -1 true false 152 143 9\n" + 	   
                "Circle -1 true false 152 166 9\n" + 	   
                "Polygon -1184463 true false 172 112 191 112 185 133 179 133\n" + 	   
                "Polygon -1184463 true false 175 6 194 6 189 21 180 21\n" + 	   
                "Line -1184463 false 149 24 197 24\n" + 	   
                "Rectangle -16777216 true false 101 177 122 187\n" + 	   
                "Rectangle -16777216 true false 179 164 183 186\n" + 	   
                "\n" + 	   
                "person service\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 180 195 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285\n" + 	   
                "Polygon -1 true false 120 90 105 90 60 195 90 210 120 150 120 195 180 195 180 150 210 210 240 195 195 90 180 90 165 105 150 165 135 105 120 90\n" + 	   
                "Polygon -1 true false 123 90 149 141 177 90\n" + 	   
                "Rectangle -7500403 true true 123 76 176 92\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Line -13345367 false 121 90 194 90\n" + 	   
                "Line -16777216 false 148 143 150 196\n" + 	   
                "Rectangle -16777216 true false 116 186 182 198\n" + 	   
                "Circle -1 true false 152 143 9\n" + 	   
                "Circle -1 true false 152 166 9\n" + 	   
                "Rectangle -16777216 true false 179 164 183 186\n" + 	   
                "Polygon -2674135 true false 180 90 195 90 183 160 180 195 150 195 150 135 180 90\n" + 	   
                "Polygon -2674135 true false 120 90 105 90 114 161 120 195 150 195 150 135 120 90\n" + 	   
                "Polygon -2674135 true false 155 91 128 77 128 101\n" + 	   
                "Rectangle -16777216 true false 118 129 141 140\n" + 	   
                "Polygon -2674135 true false 145 91 172 77 172 101\n" + 	   
                "\n" + 	   
                "person soldier\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 127 79 172 94\n" + 	   
                "Polygon -10899396 true false 105 90 60 195 90 210 135 105\n" + 	   
                "Polygon -10899396 true false 195 90 240 195 210 210 165 105\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Polygon -10899396 true false 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90\n" + 	   
                "Polygon -6459832 true false 120 90 105 90 180 195 180 165\n" + 	   
                "Line -6459832 false 109 105 139 105\n" + 	   
                "Line -6459832 false 122 125 151 117\n" + 	   
                "Line -6459832 false 137 143 159 134\n" + 	   
                "Line -6459832 false 158 179 181 158\n" + 	   
                "Line -6459832 false 146 160 169 146\n" + 	   
                "Rectangle -6459832 true false 120 193 180 201\n" + 	   
                "Polygon -6459832 true false 122 4 107 16 102 39 105 53 148 34 192 27 189 17 172 2 145 0\n" + 	   
                "Polygon -16777216 true false 183 90 240 15 247 22 193 90\n" + 	   
                "Rectangle -6459832 true false 114 187 128 208\n" + 	   
                "Rectangle -6459832 true false 177 187 191 208\n" + 	   
                "\n" + 	   
                "person student\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -13791810 true false 135 90 150 105 135 165 150 180 165 165 150 105 165 90\n" + 	   
                "Polygon -7500403 true true 195 90 240 195 210 210 165 105\n" + 	   
                "Circle -7500403 true true 110 5 80\n" + 	   
                "Rectangle -7500403 true true 127 79 172 94\n" + 	   
                "Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90\n" + 	   
                "Polygon -1 true false 100 210 130 225 145 165 85 135 63 189\n" + 	   
                "Polygon -13791810 true false 90 210 120 225 135 165 67 130 53 189\n" + 	   
                "Polygon -1 true false 120 224 131 225 124 210\n" + 	   
                "Line -16777216 false 139 168 126 225\n" + 	   
                "Line -16777216 false 140 167 76 136\n" + 	   
                "Polygon -7500403 true true 105 90 60 195 90 210 135 105\n" + 	   
                "\n" + 	   
                "plant\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 135 90 165 300\n" + 	   
                "Polygon -7500403 true true 135 255 90 210 45 195 75 255 135 285\n" + 	   
                "Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285\n" + 	   
                "Polygon -7500403 true true 135 180 90 135 45 120 75 180 135 210\n" + 	   
                "Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135\n" + 	   
                "Polygon -7500403 true true 135 105 90 60 45 45 75 105 135 135\n" + 	   
                "Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60\n" + 	   
                "Polygon -7500403 true true 135 90 120 45 150 15 180 45 165 90\n" + 	   
                "\n" + 	   
                "pumpkin\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 false true 148 30 107 33 74 44 33 58 15 105 0 150 30 240 105 285 135 285 150 270 165 285 195 285 255 255 300 150 268 62 225 43 196 36\n" + 	   
                "Polygon -7500403 true true 33 58 0 150 30 240 105 285 135 285 150 270 165 285 195 285 255 255 300 150 268 62 226 43 194 36 148 32 105 35\n" + 	   
                "Polygon -16777216 false false 108 40 75 57 42 101 32 177 79 253 124 285 133 285 147 268 122 222 103 176 107 131 122 86 140 52 154 42 193 66 204 101 216 158 212 209 188 256 164 278 163 283 196 285 234 255 257 199 268 137 251 84 229 52 191 41 163 38 151 41\n" + 	   
                "Polygon -6459832 true false 133 50 171 50 167 32 155 15 146 2 117 10 126 23 130 33\n" + 	   
                "Polygon -16777216 false false 117 10 127 26 129 35 132 49 170 49 168 32 154 14 145 1\n" + 	   
                "\n" + 	   
                "rabbit\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 61 150 76 180 91 195 103 214 91 240 76 255 61 270 76 270 106 255 132 209 151 210 181 210 211 240 196 255 181 255 166 247 151 255 166 270 211 270 241 255 240 210 270 225 285 165 256 135 226 105 166 90 91 105\n" + 	   
                "Polygon -7500403 true true 75 164 94 104 70 82 45 89 19 104 4 149 19 164 37 162 59 153\n" + 	   
                "Polygon -7500403 true true 64 98 96 87 138 26 130 15 97 36 54 86\n" + 	   
                "Polygon -7500403 true true 49 89 57 47 78 4 89 20 70 88\n" + 	   
                "Circle -16777216 true false 37 103 16\n" + 	   
                "Line -16777216 false 44 150 104 150\n" + 	   
                "Line -16777216 false 39 158 84 175\n" + 	   
                "Line -16777216 false 29 159 57 195\n" + 	   
                "Polygon -5825686 true false 0 150 15 165 15 150\n" + 	   
                "Polygon -5825686 true false 76 90 97 47 130 32\n" + 	   
                "Line -16777216 false 180 210 165 180\n" + 	   
                "Line -16777216 false 165 180 180 165\n" + 	   
                "Line -16777216 false 180 165 225 165\n" + 	   
                "Line -16777216 false 180 210 210 240\n" + 	   
                "\n" + 	   
                "rocket\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 120 165 75 285 135 255 165 255 225 285 180 165\n" + 	   
                "Polygon -1 true false 135 285 105 135 105 105 120 45 135 15 150 0 165 15 180 45 195 105 195 135 165 285\n" + 	   
                "Rectangle -7500403 true true 147 176 153 288\n" + 	   
                "Polygon -7500403 true true 120 45 180 45 165 15 150 0 135 15\n" + 	   
                "Line -7500403 true 105 105 135 120\n" + 	   
                "Line -7500403 true 135 120 165 120\n" + 	   
                "Line -7500403 true 165 120 195 105\n" + 	   
                "Line -7500403 true 105 135 135 150\n" + 	   
                "Line -7500403 true 135 150 165 150\n" + 	   
                "Line -7500403 true 165 150 195 135\n" + 	   
                "\n" + 	   
                "shark\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 283 153 288 149 271 146 301 145 300 138 247 119 190 107 104 117 54 133 39 134 10 99 9 112 19 142 9 175 10 185 40 158 69 154 64 164 80 161 86 156 132 160 209 164\n" + 	   
                "Polygon -7500403 true true 199 161 152 166 137 164 169 154\n" + 	   
                "Polygon -7500403 true true 188 108 172 83 160 74 156 76 159 97 153 112\n" + 	   
                "Circle -16777216 true false 256 129 12\n" + 	   
                "Line -16777216 false 222 134 222 150\n" + 	   
                "Line -16777216 false 217 134 217 150\n" + 	   
                "Line -16777216 false 212 134 212 150\n" + 	   
                "Polygon -7500403 true true 78 125 62 118 63 130\n" + 	   
                "Polygon -7500403 true true 121 157 105 161 101 156 106 152\n" + 	   
                "\n" + 	   
                "sheep\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -16777216 true false 166 225 195 285\n" + 	   
                "Rectangle -16777216 true false 62 225 90 285\n" + 	   
                "Rectangle -7500403 true true 30 75 210 225\n" + 	   
                "Circle -7500403 true true 135 75 150\n" + 	   
                "Circle -16777216 true false 180 76 116\n" + 	   
                "\n" + 	   
                "sheep\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 209 183 194 198 179 198 164 183 164 174 149 183 89 183 74 168 59 198 44 198 29 185 43 151 28 121 44 91 59 80 89 80 164 95 194 80 254 65 269 80 284 125 269 140 239 125 224 153 209 168\n" + 	   
                "Rectangle -7500403 true true 180 195 195 225\n" + 	   
                "Rectangle -7500403 true true 45 195 60 225\n" + 	   
                "Rectangle -16777216 true false 180 225 195 240\n" + 	   
                "Rectangle -16777216 true false 45 225 60 240\n" + 	   
                "Polygon -7500403 true true 245 60 250 72 240 78 225 63 230 51\n" + 	   
                "Polygon -7500403 true true 25 72 40 80 42 98 22 91\n" + 	   
                "Line -16777216 false 270 137 251 122\n" + 	   
                "Line -16777216 false 266 90 254 90\n" + 	   
                "\n" + 	   
                "spider\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 134 255 104 240 96 210 98 196 114 171 134 150 119 135 119 120 134 105 164 105 179 120 179 135 164 150 185 173 199 195 203 210 194 240 164 255\n" + 	   
                "Line -7500403 true 167 109 170 90\n" + 	   
                "Line -7500403 true 170 91 156 88\n" + 	   
                "Line -7500403 true 130 91 144 88\n" + 	   
                "Line -7500403 true 133 109 130 90\n" + 	   
                "Polygon -7500403 true true 167 117 207 102 216 71 227 27 227 72 212 117 167 132\n" + 	   
                "Polygon -7500403 true true 164 210 158 194 195 195 225 210 195 285 240 210 210 180 164 180\n" + 	   
                "Polygon -7500403 true true 136 210 142 194 105 195 75 210 105 285 60 210 90 180 136 180\n" + 	   
                "Polygon -7500403 true true 133 117 93 102 84 71 73 27 73 72 88 117 133 132\n" + 	   
                "Polygon -7500403 true true 163 140 214 129 234 114 255 74 242 126 216 143 164 152\n" + 	   
                "Polygon -7500403 true true 161 183 203 167 239 180 268 239 249 171 202 153 163 162\n" + 	   
                "Polygon -7500403 true true 137 140 86 129 66 114 45 74 58 126 84 143 136 152\n" + 	   
                "Polygon -7500403 true true 139 183 97 167 61 180 32 239 51 171 98 153 137 162\n" + 	   
                "\n" + 	   
                "square\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 30 30 270 270\n" + 	   
                "\n" + 	   
                "square 1\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 30 30 270 270\n" + 	   
                "Rectangle -16777216 true false 60 60 240 240\n" + 	   
                "\n" + 	   
                "squirrel\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 87 267 106 290 145 292 157 288 175 292 209 292 207 281 190 276 174 277 156 271 154 261 157 245 151 230 156 221 171 209 214 165 231 171 239 171 263 154 281 137 294 136 297 126 295 119 279 117 241 145 242 128 262 132 282 124 288 108 269 88 247 73 226 72 213 76 208 88 190 112 151 107 119 117 84 139 61 175 57 210 65 231 79 253 65 243 46 187 49 157 82 109 115 93 146 83 202 49 231 13 181 12 142 6 95 30 50 39 12 96 0 162 23 250 68 275\n" + 	   
                "Polygon -16777216 true false 237 85 249 84 255 92 246 95\n" + 	   
                "Line -16777216 false 221 82 213 93\n" + 	   
                "Line -16777216 false 253 119 266 124\n" + 	   
                "Line -16777216 false 278 110 278 116\n" + 	   
                "Line -16777216 false 149 229 135 211\n" + 	   
                "Line -16777216 false 134 211 115 207\n" + 	   
                "Line -16777216 false 117 207 106 211\n" + 	   
                "Line -16777216 false 91 268 131 290\n" + 	   
                "Line -16777216 false 220 82 213 79\n" + 	   
                "Line -16777216 false 286 126 294 128\n" + 	   
                "Line -16777216 false 193 284 206 285\n" + 	   
                "\n" + 	   
                "star\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 151 1 185 108 298 108 207 175 242 282 151 216 59 282 94 175 3 108 116 108\n" + 	   
                "\n" + 	   
                "strawberry\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 false true 149 47 103 36 72 45 58 62 37 88 35 114 34 141 84 243 122 290 151 280 162 288 194 287 239 227 284 122 267 64 224 45 194 38\n" + 	   
                "Polygon -7500403 true true 72 47 38 88 34 139 85 245 122 289 150 281 164 288 194 288 239 228 284 123 267 65 225 46 193 39 149 48 104 38\n" + 	   
                "Polygon -10899396 true false 136 62 91 62 136 77 136 92 151 122 166 107 166 77 196 92 241 92 226 77 196 62 226 62 241 47 166 57 136 32\n" + 	   
                "Polygon -16777216 false false 135 62 90 62 135 75 135 90 150 120 166 107 165 75 196 92 240 92 225 75 195 61 226 62 239 47 165 56 135 30\n" + 	   
                "Line -16777216 false 105 120 90 135\n" + 	   
                "Line -16777216 false 75 120 90 135\n" + 	   
                "Line -16777216 false 75 150 60 165\n" + 	   
                "Line -16777216 false 45 150 60 165\n" + 	   
                "Line -16777216 false 90 180 105 195\n" + 	   
                "Line -16777216 false 120 180 105 195\n" + 	   
                "Line -16777216 false 120 225 105 240\n" + 	   
                "Line -16777216 false 90 225 105 240\n" + 	   
                "Line -16777216 false 120 255 135 270\n" + 	   
                "Line -16777216 false 120 135 135 150\n" + 	   
                "Line -16777216 false 135 210 150 225\n" + 	   
                "Line -16777216 false 165 180 180 195\n" + 	   
                "\n" + 	   
                "suit diamond\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 15 45 150 150 285 255 150\n" + 	   
                "\n" + 	   
                "suit heart\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 135 43 122\n" + 	   
                "Circle -7500403 true true 43 43 122\n" + 	   
                "Polygon -7500403 true true 255 120 240 150 210 180 180 210 150 240 146 135\n" + 	   
                "Line -7500403 true 150 209 151 80\n" + 	   
                "Polygon -7500403 true true 45 120 60 150 90 180 120 210 150 240 154 135\n" + 	   
                "\n" + 	   
                "sun\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 75 75 150\n" + 	   
                "Polygon -7500403 true true 300 150 240 120 240 180\n" + 	   
                "Polygon -7500403 true true 150 0 120 60 180 60\n" + 	   
                "Polygon -7500403 true true 150 300 120 240 180 240\n" + 	   
                "Polygon -7500403 true true 0 150 60 120 60 180\n" + 	   
                "Polygon -7500403 true true 60 195 105 240 45 255\n" + 	   
                "Polygon -7500403 true true 60 105 105 60 45 45\n" + 	   
                "Polygon -7500403 true true 195 60 240 105 255 45\n" + 	   
                "Polygon -7500403 true true 240 195 195 240 255 255\n" + 	   
                "\n" + 	   
                "tank\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 144 0 159 105\n" + 	   
                "Rectangle -6459832 true false 195 45 255 255\n" + 	   
                "Rectangle -16777216 false false 195 45 255 255\n" + 	   
                "Rectangle -6459832 true false 45 45 105 255\n" + 	   
                "Rectangle -16777216 false false 45 45 105 255\n" + 	   
                "Line -16777216 false 45 75 255 75\n" + 	   
                "Line -16777216 false 45 105 255 105\n" + 	   
                "Line -16777216 false 45 60 255 60\n" + 	   
                "Line -16777216 false 45 240 255 240\n" + 	   
                "Line -16777216 false 45 225 255 225\n" + 	   
                "Line -16777216 false 45 195 255 195\n" + 	   
                "Line -16777216 false 45 150 255 150\n" + 	   
                "Polygon -7500403 true true 90 60 60 90 60 240 120 255 180 255 240 240 240 90 210 60\n" + 	   
                "Rectangle -16777216 false false 135 105 165 120\n" + 	   
                "Polygon -16777216 false false 135 120 105 135 101 181 120 225 149 234 180 225 199 182 195 135 165 120\n" + 	   
                "Polygon -16777216 false false 240 90 210 60 211 246 240 240\n" + 	   
                "Polygon -16777216 false false 60 90 90 60 89 246 60 240\n" + 	   
                "Polygon -16777216 false false 89 247 116 254 183 255 211 246 211 237 89 236\n" + 	   
                "Rectangle -16777216 false false 90 60 210 90\n" + 	   
                "Rectangle -16777216 false false 143 0 158 105\n" + 	   
                "\n" + 	   
                "target\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 0 0 300\n" + 	   
                "Circle -16777216 true false 30 30 240\n" + 	   
                "Circle -7500403 true true 60 60 180\n" + 	   
                "Circle -16777216 true false 90 90 120\n" + 	   
                "Circle -7500403 true true 120 120 60\n" + 	   
                "\n" + 	   
                "telephone\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 75 273 60 255 60 195 84 165 75 165 45 150 45 120 60 90 105 75 195 75 240 90 255 120 255 150 223 165 215 165 240 195 240 255 226 274\n" + 	   
                "Polygon -16777216 false false 75 273 60 255 60 195 105 135 105 120 105 105 120 105 120 120 180 120 180 105 195 105 195 135 240 195 240 255 225 273\n" + 	   
                "Polygon -16777216 false false 81 165 74 165 44 150 44 120 59 90 104 75 194 75 239 90 254 120 254 150 218 167 194 135 194 105 179 105 179 120 119 120 119 105 104 105 104 135 81 166 78 165\n" + 	   
                "Rectangle -16777216 false false 120 165 135 180\n" + 	   
                "Rectangle -16777216 false false 165 165 180 180\n" + 	   
                "Rectangle -16777216 false false 142 165 157 180\n" + 	   
                "Rectangle -16777216 false false 165 188 180 203\n" + 	   
                "Rectangle -16777216 false false 142 188 157 203\n" + 	   
                "Rectangle -16777216 false false 120 188 135 203\n" + 	   
                "Rectangle -16777216 false false 120 210 135 225\n" + 	   
                "Rectangle -16777216 false false 142 210 157 225\n" + 	   
                "Rectangle -16777216 false false 165 210 180 225\n" + 	   
                "Rectangle -16777216 false false 120 233 135 248\n" + 	   
                "Rectangle -16777216 false false 142 233 157 248\n" + 	   
                "Rectangle -16777216 false false 165 233 180 248\n" + 	   
                "\n" + 	   
                "tile brick\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -1 true false 0 0 300 300\n" + 	   
                "Rectangle -7500403 true true 15 225 150 285\n" + 	   
                "Rectangle -7500403 true true 165 225 300 285\n" + 	   
                "Rectangle -7500403 true true 75 150 210 210\n" + 	   
                "Rectangle -7500403 true true 0 150 60 210\n" + 	   
                "Rectangle -7500403 true true 225 150 300 210\n" + 	   
                "Rectangle -7500403 true true 165 75 300 135\n" + 	   
                "Rectangle -7500403 true true 15 75 150 135\n" + 	   
                "Rectangle -7500403 true true 0 0 60 60\n" + 	   
                "Rectangle -7500403 true true 225 0 300 60\n" + 	   
                "Rectangle -7500403 true true 75 0 210 60\n" + 	   
                "\n" + 	   
                "tile log\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 0 0 300 300\n" + 	   
                "Line -16777216 false 0 30 45 15\n" + 	   
                "Line -16777216 false 45 15 120 30\n" + 	   
                "Line -16777216 false 120 30 180 45\n" + 	   
                "Line -16777216 false 180 45 225 45\n" + 	   
                "Line -16777216 false 225 45 165 60\n" + 	   
                "Line -16777216 false 165 60 120 75\n" + 	   
                "Line -16777216 false 120 75 30 60\n" + 	   
                "Line -16777216 false 30 60 0 60\n" + 	   
                "Line -16777216 false 300 30 270 45\n" + 	   
                "Line -16777216 false 270 45 255 60\n" + 	   
                "Line -16777216 false 255 60 300 60\n" + 	   
                "Polygon -16777216 false false 15 120 90 90 136 95 210 75 270 90 300 120 270 150 195 165 150 150 60 150 30 135\n" + 	   
                "Polygon -16777216 false false 63 134 166 135 230 142 270 120 210 105 116 120 88 122\n" + 	   
                "Polygon -16777216 false false 22 45 84 53 144 49 50 31\n" + 	   
                "Line -16777216 false 0 180 15 180\n" + 	   
                "Line -16777216 false 15 180 105 195\n" + 	   
                "Line -16777216 false 105 195 180 195\n" + 	   
                "Line -16777216 false 225 210 165 225\n" + 	   
                "Line -16777216 false 165 225 60 225\n" + 	   
                "Line -16777216 false 60 225 0 210\n" + 	   
                "Line -16777216 false 300 180 264 191\n" + 	   
                "Line -16777216 false 255 225 300 210\n" + 	   
                "Line -16777216 false 16 196 116 211\n" + 	   
                "Line -16777216 false 180 300 105 285\n" + 	   
                "Line -16777216 false 135 255 240 240\n" + 	   
                "Line -16777216 false 240 240 300 255\n" + 	   
                "Line -16777216 false 135 255 105 285\n" + 	   
                "Line -16777216 false 180 0 240 15\n" + 	   
                "Line -16777216 false 240 15 300 0\n" + 	   
                "Line -16777216 false 0 300 45 285\n" + 	   
                "Line -16777216 false 45 285 45 270\n" + 	   
                "Line -16777216 false 45 270 0 255\n" + 	   
                "Polygon -16777216 false false 150 270 225 300 300 285 228 264\n" + 	   
                "Line -16777216 false 223 209 255 225\n" + 	   
                "Line -16777216 false 179 196 227 183\n" + 	   
                "Line -16777216 false 228 183 266 192\n" + 	   
                "\n" + 	   
                "tile stones\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 0 240 45 195 75 180 90 165 90 135 45 120 0 135\n" + 	   
                "Polygon -7500403 true true 300 240 285 210 270 180 270 150 300 135 300 225\n" + 	   
                "Polygon -7500403 true true 225 300 240 270 270 255 285 255 300 285 300 300\n" + 	   
                "Polygon -7500403 true true 0 285 30 300 0 300\n" + 	   
                "Polygon -7500403 true true 225 0 210 15 210 30 255 60 285 45 300 30 300 0\n" + 	   
                "Polygon -7500403 true true 0 30 30 0 0 0\n" + 	   
                "Polygon -7500403 true true 15 30 75 0 180 0 195 30 225 60 210 90 135 60 45 60\n" + 	   
                "Polygon -7500403 true true 0 105 30 105 75 120 105 105 90 75 45 75 0 60\n" + 	   
                "Polygon -7500403 true true 300 60 240 75 255 105 285 120 300 105\n" + 	   
                "Polygon -7500403 true true 120 75 120 105 105 135 105 165 165 150 240 150 255 135 240 105 210 105 180 90 150 75\n" + 	   
                "Polygon -7500403 true true 75 300 135 285 195 300\n" + 	   
                "Polygon -7500403 true true 30 285 75 285 120 270 150 270 150 210 90 195 60 210 15 255\n" + 	   
                "Polygon -7500403 true true 180 285 240 255 255 225 255 195 240 165 195 165 150 165 135 195 165 210 165 255\n" + 	   
                "\n" + 	   
                "tile water\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true -1 0 299 300\n" + 	   
                "Polygon -1 true false 105 259 180 290 212 299 168 271 103 255 32 221 1 216 35 234\n" + 	   
                "Polygon -1 true false 300 161 248 127 195 107 245 141 300 167\n" + 	   
                "Polygon -1 true false 0 157 45 181 79 194 45 166 0 151\n" + 	   
                "Polygon -1 true false 179 42 105 12 60 0 120 30 180 45 254 77 299 93 254 63\n" + 	   
                "Polygon -1 true false 99 91 50 71 0 57 51 81 165 135\n" + 	   
                "Polygon -1 true false 194 224 258 254 295 261 211 221 144 199\n" + 	   
                "\n" + 	   
                "tooth\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 75 30 60 45 45 75 45 90 60 135 73 156 75 170 60 240 60 270 75 285 90 285 105 255 135 180 150 165 165 165 180 185 195 270 210 285 240 270 245 209 244 179 237 154 237 143 255 90 255 60 225 30 210 30 180 45 135 45 90 30\n" + 	   
                "Polygon -7500403 false true 75 30 60 45 45 75 45 90 60 135 73 158 74 170 60 240 60 270 75 285 90 285 105 255 135 180 150 165 165 165 177 183 195 270 210 285 240 270 245 210 244 179 236 153 236 144 255 90 255 60 225 30 210 30 180 45 135 45 90 30 75 30\n" + 	   
                "\n" + 	   
                "train\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 30 105 240 150\n" + 	   
                "Polygon -7500403 true true 240 105 270 30 180 30 210 105\n" + 	   
                "Polygon -7500403 true true 195 180 270 180 300 210 195 210\n" + 	   
                "Circle -7500403 true true 0 165 90\n" + 	   
                "Circle -7500403 true true 240 225 30\n" + 	   
                "Circle -7500403 true true 90 165 90\n" + 	   
                "Circle -7500403 true true 195 225 30\n" + 	   
                "Rectangle -7500403 true true 0 30 105 150\n" + 	   
                "Rectangle -16777216 true false 30 60 75 105\n" + 	   
                "Polygon -7500403 true true 195 180 165 150 240 150 240 180\n" + 	   
                "Rectangle -7500403 true true 135 75 165 105\n" + 	   
                "Rectangle -7500403 true true 225 120 255 150\n" + 	   
                "Rectangle -16777216 true false 30 203 150 218\n" + 	   
                "\n" + 	   
                "train freight hopper full\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -16777216 true false 253 195 30\n" + 	   
                "Circle -16777216 true false 220 195 30\n" + 	   
                "Circle -16777216 true false 50 195 30\n" + 	   
                "Circle -16777216 true false 17 195 30\n" + 	   
                "Rectangle -16777216 true false 105 90 135 90\n" + 	   
                "Rectangle -16777216 true false 285 180 294 195\n" + 	   
                "Polygon -7500403 true true 15 105 15 120 45 180 45 195 255 195 255 180 285 120 285 105\n" + 	   
                "Rectangle -7500403 false true 15 105 285 195\n" + 	   
                "Rectangle -16777216 true false 6 180 15 195\n" + 	   
                "Polygon -7500403 true true 90 195 105 210 120 195\n" + 	   
                "Polygon -7500403 true true 135 195 150 210 165 195\n" + 	   
                "Polygon -7500403 true true 180 195 195 210 210 195\n" + 	   
                "Polygon -16777216 false false 15 105 15 120 45 180 45 195 255 195 255 180 285 120 285 105\n" + 	   
                "Line -16777216 false 60 105 60 195\n" + 	   
                "Line -16777216 false 240 105 240 195\n" + 	   
                "Line -16777216 false 180 105 180 195\n" + 	   
                "Line -16777216 false 120 105 120 195\n" + 	   
                "Polygon -6459832 true false 15 106 60 83 97 75 133 66 186 76 235 79 285 106\n" + 	   
                "\n" + 	   
                "tree\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 118 3 94\n" + 	   
                "Rectangle -6459832 true false 120 195 180 300\n" + 	   
                "Circle -7500403 true true 65 21 108\n" + 	   
                "Circle -7500403 true true 116 41 127\n" + 	   
                "Circle -7500403 true true 45 90 120\n" + 	   
                "Circle -7500403 true true 104 74 152\n" + 	   
                "\n" + 	   
                "tree pine\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -6459832 true false 120 225 180 300\n" + 	   
                "Polygon -7500403 true true 150 240 240 270 150 135 60 270\n" + 	   
                "Polygon -7500403 true true 150 75 75 210 150 195 225 210\n" + 	   
                "Polygon -7500403 true true 150 7 90 157 150 142 210 157 150 7\n" + 	   
                "\n" + 	   
                "triangle\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 30 15 255 285 255\n" + 	   
                "\n" + 	   
                "triangle 2\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 150 30 15 255 285 255\n" + 	   
                "Polygon -16777216 true false 151 99 225 223 75 224\n" + 	   
                "\n" + 	   
                "truck\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Rectangle -7500403 true true 4 45 195 187\n" + 	   
                "Polygon -7500403 true true 296 193 296 150 259 134 244 104 208 104 207 194\n" + 	   
                "Rectangle -1 true false 195 60 195 105\n" + 	   
                "Polygon -16777216 true false 238 112 252 141 219 141 218 112\n" + 	   
                "Circle -16777216 true false 234 174 42\n" + 	   
                "Rectangle -7500403 true true 181 185 214 194\n" + 	   
                "Circle -16777216 true false 144 174 42\n" + 	   
                "Circle -16777216 true false 24 174 42\n" + 	   
                "Circle -7500403 false true 24 174 42\n" + 	   
                "Circle -7500403 false true 144 174 42\n" + 	   
                "Circle -7500403 false true 234 174 42\n" + 	   
                "\n" + 	   
                "turtle\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -10899396 true false 215 204 240 233 246 254 228 266 215 252 193 210\n" + 	   
                "Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105\n" + 	   
                "Polygon -10899396 true false 105 90 75 75 55 75 40 89 31 108 39 124 60 105 75 105 90 105\n" + 	   
                "Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87\n" + 	   
                "Polygon -10899396 true false 85 204 60 233 54 254 72 266 85 252 107 210\n" + 	   
                "Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99\n" + 	   
                "\n" + 	   
                "ufo side\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -1 true false 0 150 15 180 60 210 120 225 180 225 240 210 285 180 300 150 300 135 285 120 240 105 195 105 150 105 105 105 60 105 15 120 0 135\n" + 	   
                "Polygon -16777216 false false 105 105 60 105 15 120 0 135 0 150 15 180 60 210 120 225 180 225 240 210 285 180 300 150 300 135 285 120 240 105 210 105\n" + 	   
                "Polygon -7500403 true true 60 131 90 161 135 176 165 176 210 161 240 131 225 101 195 71 150 60 105 71 75 101\n" + 	   
                "Circle -16777216 false false 255 135 30\n" + 	   
                "Circle -16777216 false false 180 180 30\n" + 	   
                "Circle -16777216 false false 90 180 30\n" + 	   
                "Circle -16777216 false false 15 135 30\n" + 	   
                "Circle -7500403 true true 15 135 30\n" + 	   
                "Circle -7500403 true true 90 180 30\n" + 	   
                "Circle -7500403 true true 180 180 30\n" + 	   
                "Circle -7500403 true true 255 135 30\n" + 	   
                "Polygon -16777216 false false 150 59 105 70 75 100 60 130 90 160 135 175 165 175 210 160 240 130 225 100 195 70\n" + 	   
                "\n" + 	   
                "van side\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 26 147 18 125 36 61 161 61 177 67 195 90 242 97 262 110 273 129 260 149\n" + 	   
                "Circle -16777216 true false 43 123 42\n" + 	   
                "Circle -16777216 true false 194 124 42\n" + 	   
                "Polygon -16777216 true false 45 68 37 95 183 96 169 69\n" + 	   
                "Line -7500403 true 62 65 62 103\n" + 	   
                "Line -7500403 true 115 68 120 100\n" + 	   
                "Polygon -1 true false 271 127 258 126 257 114 261 109\n" + 	   
                "Rectangle -16777216 true false 19 131 27 142\n" + 	   
                "\n" + 	   
                "van top\n" + 	   
                "true\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 90 117 71 134 228 133 210 117\n" + 	   
                "Polygon -7500403 true true 150 8 118 10 96 17 85 30 84 264 89 282 105 293 149 294 192 293 209 282 215 265 214 31 201 17 179 10\n" + 	   
                "Polygon -16777216 true false 94 129 105 120 195 120 204 128 180 150 120 150\n" + 	   
                "Polygon -16777216 true false 90 270 105 255 105 150 90 135\n" + 	   
                "Polygon -16777216 true false 101 279 120 286 180 286 198 281 195 270 105 270\n" + 	   
                "Polygon -16777216 true false 210 270 195 255 195 150 210 135\n" + 	   
                "Polygon -1 true false 201 16 201 26 179 20 179 10\n" + 	   
                "Polygon -1 true false 99 16 99 26 121 20 121 10\n" + 	   
                "Line -16777216 false 130 14 168 14\n" + 	   
                "Line -16777216 false 130 18 168 18\n" + 	   
                "Line -16777216 false 130 11 168 11\n" + 	   
                "Line -16777216 false 185 29 194 112\n" + 	   
                "Line -16777216 false 115 29 106 112\n" + 	   
                "Line -7500403 false 210 180 195 180\n" + 	   
                "Line -7500403 false 195 225 210 240\n" + 	   
                "Line -7500403 false 105 225 90 240\n" + 	   
                "Line -7500403 false 90 180 105 180\n" + 	   
                "\n" + 	   
                "wheel\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Circle -7500403 true true 3 3 294\n" + 	   
                "Circle -16777216 true false 30 30 240\n" + 	   
                "Line -7500403 true 150 285 150 15\n" + 	   
                "Line -7500403 true 15 150 285 150\n" + 	   
                "Circle -7500403 true true 120 120 60\n" + 	   
                "Line -7500403 true 216 40 79 269\n" + 	   
                "Line -7500403 true 40 84 269 221\n" + 	   
                "Line -7500403 true 40 216 269 79\n" + 	   
                "Line -7500403 true 84 40 221 269\n" + 	   
                "\n" + 	   
                "wolf\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 75 225 97 249 112 252 122 252 114 242 102 241 89 224 94 181 64 113 46 119 31 150 32 164 61 204 57 242 85 266 91 271 101 271 96 257 89 257 70 242\n" + 	   
                "Polygon -7500403 true true 216 73 219 56 229 42 237 66 226 71\n" + 	   
                "Polygon -7500403 true true 181 106 213 69 226 62 257 70 260 89 285 110 272 124 234 116 218 134 209 150 204 163 192 178 169 185 154 189 129 189 89 180 69 166 63 113 124 110 160 111 170 104\n" + 	   
                "Polygon -6459832 true true 252 143 242 141\n" + 	   
                "Polygon -6459832 true true 254 136 232 137\n" + 	   
                "Line -16777216 false 75 224 89 179\n" + 	   
                "Line -16777216 false 80 159 89 179\n" + 	   
                "Polygon -6459832 true true 262 138 234 149\n" + 	   
                "Polygon -7500403 true true 50 121 36 119 24 123 14 128 6 143 8 165 8 181 7 197 4 233 23 201 28 184 30 169 28 153 48 145\n" + 	   
                "Polygon -7500403 true true 171 181 178 263 187 277 197 273 202 267 187 260 186 236 194 167\n" + 	   
                "Polygon -7500403 true true 187 163 195 240 214 260 222 256 222 248 212 245 205 230 205 155\n" + 	   
                "Polygon -7500403 true true 223 75 226 58 245 44 244 68 233 73\n" + 	   
                "Line -16777216 false 89 181 112 185\n" + 	   
                "Line -16777216 false 31 150 47 118\n" + 	   
                "Polygon -16777216 true false 235 90 250 91 255 99 248 98 244 92\n" + 	   
                "Line -16777216 false 236 112 246 119\n" + 	   
                "Polygon -16777216 true false 278 119 282 116 274 113\n" + 	   
                "Line -16777216 false 189 201 203 161\n" + 	   
                "Line -16777216 false 90 262 94 272\n" + 	   
                "Line -16777216 false 110 246 119 252\n" + 	   
                "Line -16777216 false 190 266 194 274\n" + 	   
                "Line -16777216 false 218 251 219 257\n" + 	   
                "Polygon -16777216 true false 230 67 228 54 222 62 224 72\n" + 	   
                "Line -16777216 false 246 67 234 64\n" + 	   
                "Line -16777216 false 229 45 235 68\n" + 	   
                "Line -16777216 false 30 150 30 165\n" + 	   
                "\n" + 	   
                "x\n" + 	   
                "false\n" + 	   
                "0\n" + 	   
                "Polygon -7500403 true true 270 75 225 30 30 225 75 270\n\n";
        
        return formas;
    }

}
