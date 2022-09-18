if (args.Length > 0)
{
    foreach (var arg in args)
    {
        System.Diagnostics.Process process = new System.Diagnostics.Process();
        System.Diagnostics.ProcessStartInfo startInfo = new System.Diagnostics.ProcessStartInfo
        {
            WindowStyle = System.Diagnostics.ProcessWindowStyle.Hidden,
            FileName = "cmd.exe"
        };

        if (arg == "enable")
            startInfo.Arguments = "/C powercfg -H on";
        else if (arg == "disable")
            startInfo.Arguments = "/C powercfg -H off";

        process.StartInfo = startInfo;
        process.Start();
        Console.WriteLine($"Argument = {arg}");
    }
}
else
{
    Console.WriteLine("No arguments");
}
