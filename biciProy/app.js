const express = require ('express')
const conexion = require ('./database')
const app = express();

app.use(express.json())

app.listen(3000, () => {
    console.log("Listening on port 3000...")
})

app.post('/login', (req, res) => {
    const correo = req.body.correo
    const clave = req.body.clave
    console.log(correo)
    conexion.query('SELECT correo, clave FROM usuarios WHERE ? AND ?',[{correo:correo}, {clave:clave}],(error,results)=>{      
        if(error)
        {
            console.log(error)
        }else if(results!=null){
            console.log(results)
            res.status(200).send(JSON.stringify(results[0]))
        }
        else{
            console.log(results)
            res.status(404).send()
        }
    })
})

app.post('/registerUser', (req, res) => {
    const codigo = req.body.codigo
    const nombre = req.body.nombre
    const correo = req.body.correo
    const clave = req.body.clave
    const Pseguridad = req.body.Pseguridad
    const Rseguridad = req.body.Rseguridad
    const Rol_id = req.body.Rol_id

    conexion.query(`INSERT INTO usuarios (codigo,nombre,correo,clave,Pseguridad,Rseguridad,Rol_id) VALUES('${codigo}',
    '${nombre}','${correo}','${clave}',${Pseguridad},'${Rseguridad}',${Rol_id})`,(error,results)=>{
    if(error)//brb
    {
        console.log(error)
    }else if(results!=null){
        console.log(results)
        res.status(200).send(JSON.stringify(results["affectedRows"]))
    }
    else{
        res.status(404).send()
    }
})
})

app.get('/getUser/:correo', (req,res)=>{
    const correo = req.params.correo   
    console.log("Consultando a "+correo)
    conexion.query(`SELECT * FROM usuarios WHERE correo='${correo}'`,(error,results)=>{
    if(error)
    {
        console.log(error)
    }else if(results!=null){
        res.status(200).send(JSON.stringify(results[0]))
    }
    else{
        console.log("3")
        console.log(results)
        res.status(404).send()
    }
    })
})

app.get('/getUsers', (req,res)=>{
    const codigo = req.body.codigo
    console.log("Consultando a "+codigo)
    conexion.query(`SELECT * FROM usuarios`,(error,results)=>{
    if(error)
    {
        console.log(error)
    }else if(results!=null){
        res.status(200).send(JSON.stringify(results))
    }
    else{
        console.log("3")
        console.log(results)
        res.status(404).send()
    }
    })
})

app.put('/updateUser', (req,res)=>{
    const codigo = req.body.codigo
    const nombre = req.body.nombre
    const correo = req.body.correo
    const clave = req.body.clave
    const Pseguridad = req.body.Pseguridad
    const Rseguridad = req.body.Rseguridad
    const Rol_id = req.body.Rol_id
    console.log("Modificando a :"+codigo)
    conexion.query(`UPDATE usuarios SET nombre='${nombre}', clave='${clave}', Pseguridad=${Pseguridad}, Rseguridad='${Rseguridad}', Rol_id=${Rol_id} WHERE codigo=${codigo}`,(error,results)=>{
    if(error)
    {
        console.log(error)
    }else if(results!=null){
        console.log(results)
        res.status(200).send(JSON.stringify(results))
    }
    else{
        console.log(results)
        res.status(404).send()
    }
    })
})


app.patch('/updateOneUser/:palabras', (req,res)=>{
    const palabras = req.params.palabras
    console.log(palabras)
    const words = palabras.split(',')
    const valor = words[0]
    const campo = words[1]
    const codigo = words[2]
    console.log("Modificando a :"+codigo)
    conexion.query(`UPDATE usuarios SET ${campo}='${valor}' WHERE codigo=${codigo}`, ( error,results) =>{
        if(error)
        {
            console.log(error)
        }else if(results!=null){
            console.log(results)
            res.status(200).send(JSON.stringify(results["affectedRows"]))
        }
        else{
            console.log(results)
            res.status(404).send()
        }
    })
})

app.delete('/deleteUser/:codigo', (req,res)=>{
    const codigo = req.params.codigo
    console.log(codigo)
    conexion.query('DELETE FROM usuarios WHERE ?',[{codigo}],(error,results)=>{
    if(error)
    {
        console.log(error)
    }else if(results!=null){
        res.status(200).send(JSON.stringify(results["affectedRows"]))
    }
    else{
        console.log("3")
        console.log(results)
        res.status(404).send()
    }
    })
})

//↑↑↑↑↑↑va bien↑↑↑↑↑↑

app.post('/registerBike', (req, res) => {
    const idBicicleta = req.body.idBicicleta
    const cedulaPropietario = req.body.cedulaPropietario
    const fechaRegistro = req.body.fechaRegistro
    const lugarRegistro = req.body.lugarRegistro
    const Marca_id = req.body.Marca_id
    const numSerie = req.body.numSerie
    const Tipo_id = req.body.Tipo_id
    const color = req.body.color
    const Estudiante_id = req.body.Estudiante_id

    conexion.query(`INSERT INTO bicicletas (idBicicleta,cedulaPropietario,fechaRegistro,lugarRegistro,Marca_id,numSerie,Tipo_id,color,Estudiante_id) 
    VALUES('${idBicicleta}','${cedulaPropietario}','${fechaRegistro}','${lugarRegistro}',${Marca_id},'${numSerie}',${Tipo_id},'${color}','${Estudiante_id}')`,(error,results)=>{
    if(error)
    {
        console.log(error)
    }else if(results!=null){
        res.status(200).send()
    }
    else{
        res.status(404).send()
    }
})
})

app.get('/geBike', (req,res)=>{
    const Estudiante_id = req.body.Estudiante_id
    console.log(codigo)
    conexion.query('SELECT * FROM bicicletas WHERE ?',[{Estudiante_id}],(error,results)=>{
    if(error)
    {
        console.log(error)
    }else if(results!=null){
        res.status(200).send(JSON.stringify(results[0]))
    }
    else{
        console.log("3")
        console.log(results)
        res.status(404).send()
    }
    })
})
//updateBike
//deleteBike

app.post('/registerSlots', (req, res) => {
    const idCupo = req.body.idCupo
    const seccion = req.body.seccion
    const estado = req.body.estado

    conexion.query(`INSERT INTO cupos (idCupo,seccion,estado) 
    VALUES(${idCupo},'${seccion}',${fechaRegistro},${estado}')`,(error,results)=>{
    if(error)
    {
        console.log(error)
    }else if(results!=null){
        res.status(200).send()
    }
    else{
        res.status(404).send()
    }
})
})
//getSlots
//updateSlots
//deleteSlots

//registerBrands
//getBrands
//updateBrands
//deleteBrands

//registerParkinglot
//getParkinglot
//updateParkinglots
//deleteParkinglot

//registerQuestions
//getQuestions
//updateQuestions
//deleteQuestions

//registerRoles
//getRoles
//updateRoles
//deleteRoles

//registerTypes
//getTypes
//updateTypes
//deleteTypes
